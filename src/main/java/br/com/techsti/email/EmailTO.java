package br.com.techsti.email;



import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 12:31:53
 *
 */
public class EmailTO {

	private String nomeDest;
	private List<String> lstInformacoes = new ArrayList<String>();
	private String texto;
	private String mensagem; /* texto que seja encaixado na moldura de e-mail */
	private String destinatario; /* destinatario */
	private String remetente; /* e-mail de quem esta enviando */
	private String assunto; /* assunto do e-mail */
	private List<String> anexos = new ArrayList<String>(); /*
															 * lista de anexos
															 * (pathname -
															 * caminhos dos
															 * anexos)
															 */

	private String fileName;
	private String nomeAnexo;

	private String fileName2;
	private String nomeAnexo2;

	public void addAnexo(String anexo) {
		this.anexos.add(anexo);
	}

	public String getFileName2() {
		return fileName2;
	}

	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}

	public String getNomeAnexo2() {
		return nomeAnexo2;
	}

	public void setNomeAnexo2(String nomeAnexo2) {
		this.nomeAnexo2 = nomeAnexo2;
	}

	public EmailTO() {
		this.remetente = "henriquesantiagofranco@gmail.com";
	}

	public EmailTO(String destinatario, String assunto, String mensagem, String nomeDest) {
		this.destinatario = destinatario;
		this.assunto = assunto;
		this.remetente = "henriquesantiagofranco@gmail.com";
		this.nomeDest = nomeDest;
		this.mensagem = mensagem;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public List<String> getLstInformacoes() {
		return lstInformacoes;
	}

	public void setLstInformacoes(List<String> lstInformacoes) {
		this.lstInformacoes = lstInformacoes;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getNomeAnexo() {
		return nomeAnexo;
	}

	public void setNomeAnexo(String nomeAnexo) {
		this.nomeAnexo = nomeAnexo;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<String> getAnexos() {
		return anexos;
	}

	public void setAnexos(List<String> anexos) {
		this.anexos = anexos;
	}

	public String getNomeDest() {
		return nomeDest;
	}

	public void setNomeDest(String nomeDest) {
		this.nomeDest = nomeDest;
	}

	/**
	 * 
	 * @author DF-DEV-2 henriquesantiagofranco@gmail.com
	 * @date 25/11/2018 - 12:56:48
	 *
	 * RN: Utilização de pattern builder para facilitar criação do e-mail 
	 *
	 * @param assunto
	 * @return
	 */
	public EmailTO comAssunto(String assunto) {
		this.assunto = assunto;
		return this;
	}

	public EmailTO comMensagem(String mensagem) {
		this.mensagem = mensagem;
		return this;
	}

	public EmailTO comDestinatario(String destinatario) {
		this.destinatario = destinatario;
		return this;
	}

}
