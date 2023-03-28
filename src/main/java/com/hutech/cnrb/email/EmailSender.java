package com.hutech.cnrb.email;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailSender {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String toEmailId, String subject, String body) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(toEmailId);
		helper.setSubject(subject);
		message.setContent(body, "text/html; charset=utf-8");
		
//		FileSystemResource resource = new FileSystemResource(new File(attachement));
//		helper.addAttachment(resource.getFilename(), resource);
		mailSender.send(message);
	}
}
