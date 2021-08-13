package com.eldorado.userservice.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eldorado.userservice.constants.UserConstants;
import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.entities.ConfirmationToken;
import com.eldorado.userservice.entities.Role;
import com.eldorado.userservice.factories.ConfirmTokenFactory;
import com.eldorado.userservice.operations.Mailer;
import com.eldorado.userservice.repositories.ConfirmationTokenRepository;
import com.eldorado.userservice.repositories.RoleRepository;
import com.eldorado.userservice.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * RegistrationService for handling Registration related services
 */
@Slf4j
@Service
public class RegistrationService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private Mailer mailSender;

	@Autowired
	private ConfirmTokenFactory tokenFactory;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	public @Value("${baseUri}") String baseUri;

	@Autowired
	public @Value("${frontEndUri}") String frontEndUri;

	/**
	 * @param AuthUser object
	 * @return message it register user and send verification mail
	 * 
	 */
	public ResponseEntity<String> registerUser(AuthUser user) {
		String message = UserConstants.REGISTRATION_STARTED;
		log.debug(message);
		Set<Role> roles = new HashSet<>();
		Optional<Role> opRole=roleRepository.findByName(UserConstants.ROLE_USER);
		Role userRole = opRole.orElseThrow(() -> new RuntimeException(UserConstants.ROLE_NOT_FOUND));
		roles.add(userRole);
		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		List<String> newPasswordList = new ArrayList<>();
		newPasswordList.add(user.getPassword());
		user.setLastThreePasswords(newPasswordList);
		userRepository.save(user);
		ConfirmationToken confirmationToken = tokenFactory.createConfirmationToken(user);
		confirmationTokenRepository.save(confirmationToken);
		mailSender.sendVerificationEmail(user, confirmationToken);
		message = UserConstants.EMAIL_SENT_SUCCESSFULLY;
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	/**
	 * @param ConfirmationToken object
	 * @return message it verify email of User
	 * 
	 */
	public ResponseEntity<String> verifyUserAccount(AuthUser user) {
		user.setEnabled(true);
		userRepository.save(user);
		String message = UserConstants.USER_REGISTERED_SUCCESSFULLY;
		log.info(message);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

}