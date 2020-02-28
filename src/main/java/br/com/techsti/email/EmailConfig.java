package br.com.techsti.email;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 12:33:55
 *
 */
@Component
public class EmailConfig implements Serializable {

	private static final long serialVersionUID = 2373056161443943898L;

	@Value("${conta.email}")
	private String email;
	
	@Value("${conta.senha}")
	private String senha;
	
	private String protocol;
	private String smtp;
	private String port;
	private String from;
	private String user;
	private String password;
	private String name;

	public EmailConfig() {
	}

	public void enviarEmail(EmailTO dadosEmail) throws Exception {
		final Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, senha);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(dadosEmail.getRemetente())); // Remetente

			Address[] toUser = InternetAddress // Destinatário(s)
					.parse(dadosEmail.getDestinatario());

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(dadosEmail.getAssunto());// Assunto
			message.setContent(dadosEmail.getMensagem(), "text/html; charset=utf-8");
			/** Método para enviar a mensagem criada */
			Transport.send(message);

			System.out.println("Enviado!!!");
		} catch (final Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

}
