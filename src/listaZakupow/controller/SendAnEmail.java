package listaZakupow.controller;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class SendAnEmail {
	 private static final String HOST = "smtp.gmail.com";
	 private static final int PORT = 465;
	 private static final String FROM = "forExample@gmail.com";
	 private static final String PASSWORD = "1234";
	 private static final String TO = "forExample@gmail.com";
	 private static final String SUBJECT = "Shopping list";
	 private String content = "";

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void send() throws MessagingException {

	  Properties props = new Properties();
	  props.put("mail.transport.protocol", "smtps");
	  props.put("mail.smtps.auth", "true");

	  Session mailSession = Session.getDefaultInstance(props);

	  mailSession.setDebug(true);

	  MimeMessage message = new MimeMessage(mailSession);
	  message.setSubject(SUBJECT);
	  message.setContent(content, "text/plain; charset=ISO-8859-2");
	  message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));

	  Transport transport = mailSession.getTransport();
	  transport.connect(HOST, PORT, FROM, PASSWORD);

	  transport.sendMessage(message, message
	    .getRecipients(Message.RecipientType.TO));
	  transport.close();
	 }
	}