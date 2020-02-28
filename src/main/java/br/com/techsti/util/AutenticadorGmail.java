package br.com.techsti.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 13:16:15
 *
 */
public class AutenticadorGmail extends Authenticator {
	
	private String email;
	private String senha;
	
	public AutenticadorGmail(String email, String senha) {	
		this.email = email;
		this.senha = senha;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.email, this.senha);
	}
	
}
