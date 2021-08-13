package com.eldorado.userservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eldorado.userservice.constants.UserConstants;
import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.operations.PasswordChanger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChangePasswordService {

	@Autowired
	private PasswordChanger passwordChanger;

	public ResponseEntity<String> processChangePassword(AuthUser user, String newPassword) {
		String message = UserConstants.PASS_CHANGE_INITIATED_SERVICE;
		log.info(message);
		return passwordChanger.changePassword(user, newPassword);
	}

}
