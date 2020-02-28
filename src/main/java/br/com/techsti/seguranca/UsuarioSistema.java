package br.com.techsti.seguranca;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.techsti.entidade.Usuario;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 01:39:00
 *
 */
public class UsuarioSistema implements UserDetails {

	private static final long serialVersionUID = 1016445631985795710L;

	private final String id;
	private final String username;
	private final String password;
	private final Collection<? extends GrantedAuthority> authorities;
	
	private Usuario usuario;

	public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		this.id = usuario.getId().toString();
		this.username = usuario.getEmail();
		this.password = usuario.getSenha();
		this.authorities = authorities;
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	@JsonIgnore
	public String getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}