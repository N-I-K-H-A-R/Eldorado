package com.eldorado.userservice.operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.eldorado.userservice.constants.UserConstants;
import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.entities.ConfirmationToken;
import com.eldorado.userservice.entities.ForgotPasswordToken;
import com.eldorado.userservice.services.EmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Mailer {
	
	@Autowired
	private Environment environment;

	@Autowired
	public @Value("${frontEndUri}") String frontEndUri;
	
	@Autowired
	private EmailService emailService;
	
	/**
	 * @param AuthUser          object
	 * @param ConfirmationToken object
	 * @return message it send verification mail to user
	 */
	public boolean sendVerificationEmail(AuthUser user, ConfirmationToken confirmationToken) {

		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			String eldoradoMail = environment.getProperty(UserConstants.ENVIRONMENT_EMAIL);
			mailMessage.setSubject(UserConstants.REGISTRATION_EMAIL_SUBJECT);

			if (eldoradoMail != null)
				mailMessage.setFrom(eldoradoMail);

			mailMessage.setText(UserConstants.EMAIL_VERIFICATION_BODY + frontEndUri
					+ UserConstants.CONFIRM_ACCOUNT_ENDPOINT + confirmationToken.getConfirmationToken());

			emailService.sendEmail(mailMessage);
			String message = UserConstants.EMAIL_SENT_SUCCESSFULLY;
			log.info(message);
			return true;
		} catch (MailException e) {
			log.error(e.toString());
		}
		return false;
	}
	

	/**
	 * @param UserEntity
	 * @param ForgotPasswordToken
	 * @return message it send Password Recovery mail to user
	 *
	 */
	public boolean sendPasswordRecoveryEmail(AuthUser user, ForgotPasswordToken forgotPasswordToken) {

		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			String eldoradoMail = environment.getProperty(UserConstants.ENVIRONMENT_EMAIL);
			mailMessage.setSubject(UserConstants.FORGOT_PASS_EMAIL_SUBJECT);

			if (eldoradoMail != null)
				mailMessage.setFrom(eldoradoMail);

			mailMessage.setText(frontEndUri + UserConstants.CONFIRM_FORGOT_PASS_FRONTEND
					+ forgotPasswordToken.getForgotPassToken());

			emailService.sendEmail(mailMessage);
			log.info(UserConstants.EMAIL_SENT_SUCCESSFULLY);
			return true;
		} catch (MailException e) {
			log.error(e.toString());
		}
		return false;
	}
}
