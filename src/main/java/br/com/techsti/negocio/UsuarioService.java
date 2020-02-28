package br.com.techsti.negocio;

import static br.com.techsti.util.Utilitarios.isPreenchimentoNuloOuVazio;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.techsti.email.EmailTO;
import br.com.techsti.email.EmailTemplate;
import br.com.techsti.entidade.Amigos;
import br.com.techsti.entidade.Usuario;
import br.com.techsti.excecao.NegocioException;
import br.com.techsti.repositorio.UsuarioRepository;
import br.com.techsti.util.Utilitarios;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 00:59:40
 *
 */
@Service
public class UsuarioService extends GenericoService<Usuario> implements Serializable {

	private static final long serialVersionUID = -2877744107065885986L;

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public List<Usuario> findAll() {
		return (List<Usuario>) repository.findAll();
	}

	public Usuario findOne(Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		return null;
	}

	public void gravar(Usuario usuario) throws NegocioException {
		this.validarDadosObrigatorios(usuario);
		this.validarSeJaExisteEmailCadastrado(usuario);
		Usuario usuarioPersistido = repository.save(usuario);
		if (isPreenchimentoNuloOuVazio(usuarioPersistido) || isPreenchimentoNuloOuVazio(usuarioPersistido.getId())){
			dispararMensagemExcecaoNegocio("Não foi possível criar usuário!");
		}
	}

	private void validarSeJaExisteEmailCadastrado(Usuario usuario) {
		if (Utilitarios.isPreenchimentoNuloOuVazio(usuario.getId()) && !Utilitarios.isPreenchimentoNuloOuVazio(this.findByEmail(usuario.getEmail()))){
			dispararMensagemExcecaoNegocio("Este e-mail já esta sendo utilizado, favor tentar outro!");
		}
	}

	/**
	 * @author Henrique Santiago henriquesantiagofranco@gmail.com
	 * @date 25/11/2018 - 01:00:39
	 *
	 *       RN: Valida dados obrigatórios para cadastro do usuário
	 *
	 * @param usuario
	 */
	@Override
	protected void validarDadosObrigatorios(Usuario usuario) throws NegocioException {
		if (isPreenchimentoNuloOuVazio(usuario)) {
			dispararMensagemExcecaoNegocio("Dados do usuário é um campo obrigatório!");
		}
		if (isPreenchimentoNuloOuVazio(usuario.getNome())) {
			dispararMensagemExcecaoNegocio("Nome do Usuário é um campo obrigatório!");
		}
		if (isPreenchimentoNuloOuVazio(usuario.getSenha())) {
			dispararMensagemExcecaoNegocio("Senha é um campo obrigatório!");
		}
		if (isPreenchimentoNuloOuVazio(usuario.getEmail())) {
			dispararMensagemExcecaoNegocio("Email é um campo obrigatório!");
		}
	}

	public Usuario findByEmail(String email) {
		Page<Usuario> usuarios = repository.findByEmail(email, PageRequest.of(0, 1));
		return usuarios.getContent() != null && !usuarios.getContent().isEmpty() ?  usuarios.getContent().get(0) : null;
	}
	
	/**
	 * TODO 
	 * Refatorando para excluir a regra de esqueci senha
	 */
	public void gerarNovaSenha(String email) {
		if (Utilitarios.isPreenchimentoNuloOuVazio(email)){
			dispararMensagemExcecaoNegocio("É necessário informar o e-mail para recuperação de senha!");
		}
		Usuario usuario = this.findByEmail(email);
		if (Utilitarios.isPreenchimentoNuloOuVazio(usuario)
				|| Utilitarios.isPreenchimentoNuloOuVazio(usuario.getId())) {
			dispararMensagemExcecaoNegocio("Usuário não encontrado em nossa base dados");
		}
		String novaSenha = Utilitarios.gerarNumeroInscricao();
		usuario.setSenha(passwordEncoder.encode(novaSenha));
		this.atualizarUsuario(usuario);
		this.enviarEmailNovaSenha(email, novaSenha);
	}

	private void enviarEmailNovaSenha(String email, String novaSenha) throws NegocioException {
		try {
			this.email.enviarEmail(new EmailTO().comAssunto("Recuperação de Senha")
					.comMensagem(EmailTemplate.prepararEmailNovaSenha(email, novaSenha))
					.comDestinatario(email));
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e.getMessage());
		}
	}

	public void atualizarUsuario(Usuario usuario) {
		this.repository.save(usuario);
	}

	/**
	 * 
	 * @author DF-DEV-2 henriquesantiagofranco@gmail.com
	 * @date 28/11/2018 - 16:33:27
	 *
	 * RN: Raliza o vinculo de amizade entre convidante e convidado
	 *
	 * @param usuarioConvidante
	 * @param emailConvidado
	 */
	public void fazerVinculoDeAmizadeComConvidante(Usuario usuarioConvidante, String emailConvidado) {
		Usuario convidado = this.findByEmail(emailConvidado);
		Amigos amigo = new Amigos();
		amigo.setConvidante(usuarioConvidante);
		amigo.setAtivo(Boolean.TRUE);
		amigo.setAmigo(convidado);
		usuarioConvidante.getListaAmigos().add(amigo);
		this.atualizarUsuario(usuarioConvidante);
	}

}
