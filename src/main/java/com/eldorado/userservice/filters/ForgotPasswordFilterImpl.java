package com.eldorado.userservice.filters;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eldorado.userservice.constants.UserConstants;
import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.entities.ForgotPasswordToken;
import com.eldorado.userservice.exceptions.EmptyFieldException;
import com.eldorado.userservice.exceptions.InvalidDataException;
import com.eldorado.userservice.operations.ValidateFields;
import com.eldorado.userservice.repositories.ForgotPasswordTokenRepository;
import com.eldorado.userservice.repositories.UserRepository;
import com.eldorado.userservice.services.ForgotPasswordServiceImpl;

import lombok.extern.slf4j.Slf4j;

//Component
@Slf4j
@Service
public class ForgotPasswordFilterImpl implements ForgotPasswordFilter {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ForgotPasswordTokenRepository forgotPasswordTokenRepository;
	@Autowired
	private Environment environment;
	@Autowired
	private ForgotPasswordServiceImpl forgotPasswordService;

	@Autowired
	private ValidateFields validateFields;

	public ResponseEntity<String> processForgotPasswordFilter(String email) {
		String message = UserConstants.PROCESS_FORGOT_PASS_FILTER;
		log.info(message);

		try {
			if (validateFields.validateUsername(email)) {
				AuthUser user = userRepository.findByEmailIgnoreCase(email);

				if (user == null) {
					message = UserConstants.NO_USER_FOUND;
					log.error(message);
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
				}

				if (Boolean.FALSE.equals(user.getEnabled())) {
					message = UserConstants.NOT_VERIFIED;
					log.error(message);
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
				}

				return forgotPasswordService.processForgotPassword(user);
			} else {
				message = UserConstants.INVALID_EMAIL_FORMAT;
				log.error(message);
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
			}
		} catch (EmptyFieldException | InvalidDataException e) {
			message = "emptyException";
			log.info(message);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		}
	}

	public ResponseEntity<String> showResetPasswordFormFilter(String forgotPasswordToken) {
		String message = UserConstants.RESET_PASS_FORM_FILTER;
		log.info(message);

		ForgotPasswordToken token = forgotPasswordTokenRepository.findByForgotPassToken(forgotPasswordToken);

		if (token == null) {
			message = UserConstants.INVALID_TOKEN;
			log.error(message);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		}
		Integer valid = 120;
		try {
			valid = Integer.parseInt(environment.getProperty("validduration"));
		} catch (NumberFormatException ex) {
			log.error(ex.toString());
		}

		if (Duration.between(token.getCreatedDateTime(), LocalDateTime.now()).toSeconds() >= valid) {
			message = UserConstants.TOKEN_EXPIRED;
			log.error(message);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		}

		message = UserConstants.VALID_TOKEN;
		log.info(message);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	public ResponseEntity<String> processResetPasswordFilter(String forgotPasswordToken, String newPassword) {
		String message = UserConstants.PASS_CHANGE_INITIATED_FILTER;
		log.info(message);
		ForgotPasswordToken token = forgotPasswordTokenRepository.findByForgotPassToken(forgotPasswordToken);

		if (token == null) {
			message = UserConstants.INVALID_TOKEN;
			log.error(message);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		}

		try {
			if (validateFields.validatePassword(newPassword)) {
				AuthUser user = token.getUser();
				return forgotPasswordService.processResetPassword(user, newPassword);
			} else {
				message = UserConstants.INVALID_PASS_FORMAT;
				log.error(message);
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
			}
		} catch (EmptyFieldException  e) {
			message = UserConstants.PASS_CANNOT_BE_EMPTY;
			log.info(message);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		}
		catch(InvalidDataException e) {
			message = UserConstants.PASS_DESCRIPTION;
			log.info(message);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		}

	}
}
