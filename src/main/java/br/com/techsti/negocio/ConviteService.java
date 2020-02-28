package br.com.techsti.negocio;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.techsti.email.EmailTO;
import br.com.techsti.email.EmailTemplate;
import br.com.techsti.entidade.Convite;
import br.com.techsti.entidade.Usuario;
import br.com.techsti.excecao.NegocioException;
import br.com.techsti.repositorio.ConviteRepository;
import br.com.techsti.util.ResponseData;
import br.com.techsti.util.Utilitarios;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 01:09:50
 *
 */
@Service
public class ConviteService extends GenericoService<Convite> implements Serializable {

	private static final long serialVersionUID = 5271967465374068979L;

	@Autowired
	private ConviteRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	/**
	 * 
	 * @author Henrique Santiago henriquesantiagofranco@gmail.com
	 * @date 25/11/2018 - 13:05:21
	 *
	 *       RN: Gera um convite para o usuário que acabou de se cadastrar, como ele
	 *       não foi convidado por ninguém o convite é gerado automaticamente
	 *
	 * @param usuario
	 */
	public void gerarConvite(Convite convite) {
		this.prepararDadosDoConvite(convite);
		Convite convitePersistido = this.repository.save(convite);
		if (Utilitarios.isPreenchimentoNuloOuVazio(convitePersistido)
				|| Utilitarios.isPreenchimentoNuloOuVazio(convitePersistido.getId())) {
			dispararMensagemExcecaoNegocio("Não foi possível gerar o convite!");
		}
		this.enviarEmailParaConvidadoComLinkDeInscricao(convite);
	}

	private void prepararDadosDoConvite(Convite convite) {
		convite.setAceito(Boolean.FALSE);
		convite.setDataHoraExpiracao(Utilitarios.adicionarDias(new Date(), 1L));
		convite.setInscricao(Utilitarios.gerarIdentificadorInscricao());
		convite.setLink(utilMessage.getMessageKey("uriconfirmacao").concat(convite.getInscricao()));
	}

	/**
	 * 
	 * @author Henrique Santiago henriquesantiagofranco@gmail.com
	 * @date 25/11/2018 - 21:09:01
	 *
	 *       RN: Realiza a confirmação da inscrição do convidado, a partir daqui o
	 *       link é desconsiderado não podendo mais ser utilizado
	 *
	 * @param inscricao
	 */
	public ResponseData realizarAceiteDoConvite(Convite convite) {
		this.validarRegrasParaConfirmacao(convite);
		if (!convite.getAutomatico()
				&& Utilitarios.isPreenchimentoNuloOuVazio(this.usuarioService.findByEmail(convite.getEmailConvidado()))) {
			return new ResponseData(convite, "É necessário realizar o cadastro antes da ativação!", HttpStatus.BAD_REQUEST.value());
		}
		convite.setAceito(Boolean.TRUE);
		this.repository.save(convite);
		this.usuarioService.fazerVinculoDeAmizadeComConvidante(convite.getConvidante(), convite.getEmailConvidado());
		this.fazerAtivacaoDoUsuario(convite.getEmailConvidado());
		return new ResponseData(null, "Amizade realizada com sucesso, acesso liberado!");
	}


	private void fazerAtivacaoDoUsuario(String emailConvidado) {
		Usuario usuario = this.usuarioService.findByEmail(emailConvidado);
		usuario.setAtivo(Boolean.TRUE);
		this.usuarioService.atualizarUsuario(usuario);
	}

	private void validarRegrasParaConfirmacao(Convite convite) {
		if (Utilitarios.isPreenchimentoNuloOuVazio(convite)
				|| Utilitarios.isPreenchimentoNuloOuVazio(convite.getId())) {
			dispararMensagemExcecaoNegocio("Houve uma falha na confirmação, inscrição inválida!");
		}
		if (Utilitarios.dataMaior(new Date(), convite.getDataHoraExpiracao())) {
			dispararMensagemExcecaoNegocio("Convite expirado, solicite outro convite ao seu amigo.");
		}
		if (convite.getAceito()) {
			dispararMensagemExcecaoNegocio("Esse convite não pode ser mais utilizado!");
		}
	}

	/**
	 * 
	 * @author Henrique Santiago henriquesantiagofranco@gmail.com
	 * @date 25/11/2018 - 13:07:43
	 *
	 *       RN: Envia e-mail para inscrição através do convite enviado
	 *
	 * @param convite
	 * @throws NegocioException
	 */
	public void enviarEmailParaConvidadoComLinkDeInscricao(Convite convite) throws NegocioException {
		try {
			this.email.enviarEmail(new EmailTO().comAssunto("Convite - Rede de Amigos")
					.comMensagem(EmailTemplate.prepararEmailDoConvidadoComDadosDoConvite(convite))
					.comDestinatario(convite.getEmailConvidado()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e.getMessage());
		}
	}

	public Convite findByInscricao(String inscricao) {
		return this.repository.findByInscricao(inscricao);
	}

}
