package br.com.techsti.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tab_amigos")
public class Amigos implements Serializable {

	private static final long serialVersionUID = 2263739500449227480L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_convidante")
	private Usuario convidante;

	@ManyToOne
	@JoinColumn(name = "id_amigo")
	private Usuario amigo;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "ativo")
	private Boolean ativo = Boolean.TRUE;

	public Amigos() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getConvidante() {
		return convidante;
	}

	public void setConvidante(Usuario convidante) {
		this.convidante = convidante;
	}

	public Usuario getAmigo() {
		return amigo;
	}

	public void setAmigo(Usuario amigo) {
		this.amigo = amigo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
