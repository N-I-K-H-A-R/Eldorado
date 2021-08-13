package com.eldorado.userservice.operations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eldorado.userservice.constants.UserConstants;
import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PasswordChanger {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	public ResponseEntity<String> changePassword(AuthUser user, String newPassword) {
		String message = UserConstants.CHANGING_PASS;
		log.info(message);

		if (user == null) {
			message = UserConstants.NO_USER_FOUND;
			log.error(message);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		}

		List<String> passwords = user.getLastThreePasswords();

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		for (String oldPassword : passwords) {
			if (encoder.matches(newPassword, oldPassword)) {
				message = UserConstants.LAST_THREE_PASSES;
				log.error(message);
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
			}
		}

		if (passwords.size() == 3)
			passwords.remove(0);

		user.setPassword(passwordEncoder.encode(newPassword));
		passwords.add(user.getPassword());
		user.setLastThreePasswords(passwords);
		userRepository.save(user);
		message = UserConstants.PASS_CHANGED;
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
}
