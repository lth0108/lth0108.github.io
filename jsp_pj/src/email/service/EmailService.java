package email.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import email.model.dto.EmailDTO;

public class EmailService {

	public void mailSender(EmailDTO dto) throws Exception {
		String host = "smtp.gmail.com"; //smtp 서버 주소
		String username = "taehan0108"; //@gmail.com 은 입력안한다.
		String password = "Rhdqjffp23@";//gmail비밀번호
		int port = 587; //포트번호, 회사마다 다르다..
		
		String fromName = dto.getFromName();
		String fromEmail = dto.getFromEmail();
		String toEmail = dto.getToEmail();
		String subject = dto.getSubject();
		String content = dto.getContent();
		
		Properties props = System.getProperties(); //import java.util.Properties;
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		//import javax.mail.Session;
		Session session = Session.getDefaultInstance(props, 
				new javax.mail.Authenticator() {
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new javax.mail.PasswordAuthentication(username, password);
					}
				}
		);
		
		session.setDebug(true);
		Message mimeMessage = new MimeMessage(session);
		mimeMessage.addFrom(new InternetAddress[] {
				new InternetAddress(fromEmail, fromName)
		});
		
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		mimeMessage.setSubject(subject);
		mimeMessage.setText(content);
		Transport.send(mimeMessage);
	}
	
}