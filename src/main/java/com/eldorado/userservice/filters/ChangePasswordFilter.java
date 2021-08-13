package com.eldorado.userservice.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eldorado.userservice.constants.UserConstants;
import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.exceptions.EmptyFieldException;
import com.eldorado.userservice.exceptions.InvalidDataException;
import com.eldorado.userservice.operations.ValidateFields;
import com.eldorado.userservice.repositories.UserRepository;
import com.eldorado.userservice.services.ChangePasswordService;
import com.eldorado.userservice.services.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChangePasswordFilter {

	@Autowired
	RegistrationService registrationService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ValidateFields validateFields;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	ChangePasswordService changePasswordService;

	public ResponseEntity<String> processChangePasswordFilter(String email, String currentPassword,
			String newPassword) {
		String message = UserConstants.PASS_CHANGE_INITIATED_FILTER;
		log.info(message);

		AuthUser user = userRepository.findByEmailIgnoreCase(email);
		if (user != null) {
			
				if (passwordEncoder.matches(currentPassword, user.getPassword())) {
					try {
					if (validateFields.validatePassword(newPassword)) {
						return changePasswordService.processChangePassword(user, newPassword);
					} else {
						message = UserConstants.INVALID_PASS_FORMAT;
						log.error(message);
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
					}
					}
					catch (EmptyFieldException e) {
						message = UserConstants.PASS_CANNOT_BE_EMPTY;
						log.info(message);
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
					} catch (InvalidDataException e) {
						message = UserConstants.PASS_DESCRIPTION;
						log.info(message);
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
					}
					
				} else {
					message = UserConstants.INVALID_PASS;
					log.error(message);
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
				}
		} else {
			message = UserConstants.NO_USER_FOUND;
			log.error(message);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		}

	}
}
