package br.com.techsti.seguranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.techsti.entidade.Usuario;
import br.com.techsti.excecao.NegocioException;
import br.com.techsti.negocio.UsuarioService;
import br.com.techsti.util.Utilitarios;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 04:09:46
 *
 */
@Component
public class DadosDoUsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.findByEmail(username);
		if (!Utilitarios.isPreenchimentoNuloOuVazio(usuario)
				&& !Utilitarios.isPreenchimentoNuloOuVazio(usuario.getId())) {
			if (!usuario.getAtivo()) {
				throw new NegocioException("Usuário ainda não foi ativado no sistema!");
			}
			return new UsuarioSistema(usuario, getGrupos(usuario));
		}
		throw new UsernameNotFoundException("Usuário ou senha incorretos!");
	}

	/**
	 * 
	 * @author Henrique Santiago henriquesantiagofranco@gmail.com
	 * @date 25/11/2018 - 04:09:57
	 *
	 *       RN: Como não foi especificado perfil, crie um para todo mundo
	 *
	 * @param usuario
	 * @return
	 */
	private static Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USUARIO"));
		return authorities;
	}

}