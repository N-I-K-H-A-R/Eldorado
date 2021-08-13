package com.eldorado.userservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eldorado.userservice.constants.UserConstants;
import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.entities.ForgotPasswordToken;
import com.eldorado.userservice.factories.ForgotPasswordTokenFactory;
import com.eldorado.userservice.operations.Mailer;
import com.eldorado.userservice.operations.PasswordChanger;
import com.eldorado.userservice.repositories.ForgotPasswordTokenRepository;
import com.eldorado.userservice.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * ForgotPasswordService for handling Forgot Password related services
 */
@Slf4j
@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private ForgotPasswordTokenRepository forgotPasswordTokenRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private Mailer mailSender;

	@Autowired
	private PasswordChanger passwordChanger;

	@Autowired
	private ForgotPasswordTokenFactory tokenFactory;

	@Autowired
	public @Value("${baseUri}") String baseUri;

	@Autowired
	public @Value("${frontEndUri}") String frontEndUri;

	@Autowired
	public @Value("${validduration}") Integer validDuration;

	/**
	 * @param email
	 * @return message it Password Recovery Mail Sent and send Mail to email
	 *
	 */

	public ResponseEntity<String> processForgotPassword(AuthUser user) {
		String message;

		ForgotPasswordToken forgotPasswordToken = tokenFactory.createForgotPasswordToken(user);

		forgotPasswordTokenRepository.save(forgotPasswordToken);
		mailSender.sendPasswordRecoveryEmail(user, forgotPasswordToken);

		message = UserConstants.PASS_RECOVERY_EMAIL_SENT;
		log.info(message);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	/**
	 * @param String object
	 * @param String object
	 * @return message it update Password of user
	 *
	 */

	public ResponseEntity<String> processResetPassword(AuthUser user, String newPassword) {
		String message = UserConstants.PASS_CHANGE_INITIATED_SERVICE;
		log.info(message);

		return passwordChanger.changePassword(user, newPassword);
	}
}
