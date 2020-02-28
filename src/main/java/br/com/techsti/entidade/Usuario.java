package br.com.techsti.entidade;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 00:18:25
 *
 */
@Entity
@Table(name = "tab_usuario")
@EntityListeners(value = UsuarioListenerHandler.class)
public class Usuario implements Serializable {

	private static final long serialVersionUID = 8592934702254547062L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "email")
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "senha")
	private String senha;

	@Column(name = "ativo")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ativo = Boolean.FALSE;

	@JsonManagedReference
	@OneToMany(mappedBy = "convidante", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Convite> listaConvites = new HashSet<>();

	@JsonManagedReference
	@OneToMany(mappedBy = "convidante", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Amigos> listaAmigos = new HashSet<>();

	public Usuario() {
	}

	public Usuario(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Set<Convite> getListaConvites() {
		return listaConvites;
	}

	public void setListaConvites(Set<Convite> listaConvites) {
		this.listaConvites = listaConvites;
	}

	public Set<Amigos> getListaAmigos() {
		return listaAmigos;
	}

	public void setListaAmigos(Set<Amigos> listaAmigos) {
		this.listaAmigos = listaAmigos;
	}

}