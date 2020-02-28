/**
 * 
 */
package br.com.techsti.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 00:20:28
 *
 */
@Entity
@Table(name = "tab_convite")
public class Convite implements Serializable {

	private static final long serialVersionUID = -737399331720076283L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dthrexpiracao")
	private Date dataHoraExpiracao;

	@Column(name = "link")
	private String link;

	@Column(name = "aceito")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean aceito = Boolean.FALSE;

	@JsonBackReference
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_convidante")
	private Usuario convidante;

	@Column(name = "emailconvidado")
	private String emailConvidado;

	@Column(name = "automatico")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean automatico = Boolean.FALSE;

	@Column(name = "inscricao")
	private String inscricao;

	public Convite() {
	}

	public Convite(Usuario usuario, Boolean automatico) {
		this.convidante = usuario;
		this.automatico = automatico;
		if (this.automatico) {
			this.emailConvidado = this.convidante.getEmail();
		}
	}

	public Convite(Usuario usuario, String emailConvidado) {
		this.convidante = usuario;
		this.emailConvidado = emailConvidado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataHoraExpiracao() {
		return dataHoraExpiracao;
	}

	public void setDataHoraExpiracao(Date dataHoraExpiracao) {
		this.dataHoraExpiracao = dataHoraExpiracao;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Boolean getAceito() {
		return aceito;
	}

	public void setAceito(Boolean aceito) {
		this.aceito = aceito;
	}

	public Usuario getConvidante() {
		return convidante;
	}

	public void setConvidante(Usuario convidante) {
		this.convidante = convidante;
	}

	public String getEmailConvidado() {
		return emailConvidado;
	}

	public void setEmailConvidado(String emailConvidado) {
		this.emailConvidado = emailConvidado;
	}

	public Boolean getAutomatico() {
		return automatico;
	}

	public void setAutomatico(Boolean automatico) {
		this.automatico = automatico;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

}
