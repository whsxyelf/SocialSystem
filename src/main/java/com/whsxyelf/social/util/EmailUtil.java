package com.whsxyelf.social.util;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.whsxyelf.social.config.EmailConfiguration;

@Service
public class EmailUtil {
	@Autowired
	private EmailConfiguration emailConfiguration;
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendSimpleEmail(String sendTo,String title,String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailConfiguration.getEmailFrom());
		message.setTo(sendTo);
		message.setSubject(title);
		message.setText(content);
		mailSender.send(message);
	}
	
	public void sendInlineMail(String sendTo,String title,String content) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
			helper.setFrom(emailConfiguration.getEmailFrom());
			helper.setTo(sendTo);
			helper.setSubject(title);
			helper.setText(content);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mailSender.send(mimeMessage);
	}

}
