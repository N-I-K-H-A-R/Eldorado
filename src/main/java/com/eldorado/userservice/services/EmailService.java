package com.eldorado.userservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * EmailService class for handling java mail sender
 * 
 */
@Service("emailService")
public class EmailService {

	private JavaMailSender javaMailSender;

	/**
	 * @param JavaMailSender reference
	 * 
	 */
	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/**
	 * @param SimpleMailMessage reference
	 * 
	 */
	@Async
	public void sendEmail(SimpleMailMessage email) {
		javaMailSender.send(email);
	}
}