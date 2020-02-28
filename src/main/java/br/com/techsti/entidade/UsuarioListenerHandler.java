package br.com.techsti.entidade;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 04:09:04
 *
 */
@Component
public class UsuarioListenerHandler {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PrePersist
	public void antesDeSalvar(final Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
	}

}
