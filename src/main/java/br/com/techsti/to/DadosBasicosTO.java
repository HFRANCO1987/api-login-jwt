package br.com.techsti.to;

import java.io.Serializable;

import br.com.techsti.entidade.Amigos;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 14:44:19
 *
 */
public class DadosBasicosTO implements Serializable {

	private static final long serialVersionUID = -2611049643674691591L;

	private String inscricao;
	private String senha;
	private String email;
	
	//Email convidante
	private String convidante;
	
	//Amigo que será desfeita a amizade
	private Amigos amigo;

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConvidante() {
		return convidante;
	}

	public void setConvidante(String convidante) {
		this.convidante = convidante;
	}

	public Amigos getAmigo() {
		return amigo;
	}

	public void setAmigo(Amigos amigo) {
		this.amigo = amigo;
	}


	
}
