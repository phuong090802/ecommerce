package com.ute.ecwebapp.service.impl;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ute.ecwebapp.dto.EmailDto;
import com.ute.ecwebapp.service.EmailService;
import com.ute.ecwebapp.util.EmailConfiguration;

@Service
public class EmailServiceImpl implements EmailService {
	private final String marker = "marker";

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	@Autowired
	private EmailConfiguration emailConfiguration;
	
	
	@Override
	public void sendEmail(String receiver, String otp) {
		EmailDto emailDto = new EmailDto();
		var messageBody = emailConfiguration.getMessageBody().replace(marker, otp);
		emailDto.setEmailName(emailConfiguration.getEmailName());
		emailDto.setMessegeBody(messageBody);
		emailDto.setReceiver(receiver);
		emailDto.setSubject(emailConfiguration.getSubject());
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(new InternetAddress(sender, emailDto.getEmailName()));
			mimeMessageHelper.setTo(emailDto.getReceiver());
			mimeMessageHelper.setText(emailDto.getMessegeBody(), true);
			mimeMessageHelper.setSubject(emailDto.getSubject());
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
