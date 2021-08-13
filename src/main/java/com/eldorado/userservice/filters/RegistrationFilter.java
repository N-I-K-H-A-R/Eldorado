package com.eldorado.userservice.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eldorado.userservice.constants.UserConstants;
import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.entities.ConfirmationToken;
import com.eldorado.userservice.exceptions.EmptyFieldException;
import com.eldorado.userservice.exceptions.InvalidDataException;
import com.eldorado.userservice.operations.ValidateFields;
import com.eldorado.userservice.repositories.ConfirmationTokenRepository;
import com.eldorado.userservice.repositories.UserRepository;
import com.eldorado.userservice.services.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RegistrationFilter {
	@Autowired
	RegistrationService registrationService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	private ValidateFields validateFields;

	/**
	 * @param AuthUser object
	 * @return message it register user and send verification mail
	 * 
	 */
	public ResponseEntity<String> registerUserFilter(AuthUser user) {
		String message = UserConstants.REGISTRATION_STARTED;
		ResponseEntity<String> res = null;
		log.debug(message);
		try {
			if (validateFields.validateUsername(user.getEmail())
					&& validateFields.validatePassword(user.getPassword())) {
				AuthUser usercheck = userRepository.findByEmailIgnoreCase(user.getEmail());
				if (usercheck == null) {
					return registrationService.registerUser(user);

				} else {

					if (Boolean.FALSE.equals(usercheck.getEnabled())) {
						message = UserConstants.VERIFICATION_EMAIL_SENT;
						log.error(message);
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
					} else {
						message = UserConstants.USER_ALREADY_EXIST_EXCEPTION + user.getEmail()
								+ UserConstants.PLEASE_LOGIN;
						log.error(message);
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
					}
				}
			}
		} catch (EmptyFieldException e) {
			if (user.getEmail().isEmpty())
				message = UserConstants.USERNAME_CANNOT_BE_EMPTY;
			else if (user.getPassword().isEmpty())
				message = UserConstants.PASS_CANNOT_BE_EMPTY;
			log.info(message);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		} catch (InvalidDataException e) {
			message = UserConstants.PASS_DESCRIPTION;
			log.info(message);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		}
		return res;

	}

	/**
	 * @param username
	 * @return message it verify email of user is USER_VERIFIED or not
	 * 
	 */
	public ResponseEntity<String> confirmValidUserFilter(String username) {
		String message;

		AuthUser user = userRepository.findByEmailIgnoreCase(username);
		if (user != null) {
			if (Boolean.TRUE.equals(user.getEnabled())) {
				message = UserConstants.USER_VERIFIED;
				log.info(message);
				return ResponseEntity.status(HttpStatus.OK).body(message);

			} else {
				message = UserConstants.NOT_VERIFIED;
				log.info(message);
				return ResponseEntity.status(HttpStatus.OK).body(message);
			}
		} else {
			message = UserConstants.NO_USER_FOUND;
			log.error(message);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		}

	}

	/**
	 * @param ConfirmationToken object
	 * @return message it verify email of User
	 * 
	 */
	public ResponseEntity<String> verifyUserAccountFilter(String confirmedToken) {
		String message;
		if (confirmedToken.isBlank()) {
			message = UserConstants.TOKEN_CANNOT_BE_EMPTY;
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		}

		ConfirmationToken token = confirmationTokenRepository.findByConfirmedToken(confirmedToken);

		if (token != null) {
			AuthUser user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
			if (Boolean.TRUE.equals(user.getEnabled())) {
				message = UserConstants.TOKEN_ALREADY_USED;
				log.error(message);
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
			}

			return registrationService.verifyUserAccount(user);
		} else {
			message = UserConstants.INVALID_TOKEN;
			log.error(message);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		}

	}

}
