package com.code;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

	public static void sendMail(String recipient) {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		String myEmail="paulineyvana23@gmail.com";
		String password="dvfoxoyiqcggkzrg";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myEmail, password);
			}
		});
		
		Message message = prepareMessage(session, myEmail, password, recipient);
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	
	private static Message prepareMessage(Session session, String myEmail, String password, String recipient) {
		Message message = new MimeMessage(session);
		try {
				message.setFrom(new InternetAddress(myEmail));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
				message.setSubject("Request to change password");
				message.setText("http://localhost:8080/resetpasswd/" + recipient);
				return message;
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}

}