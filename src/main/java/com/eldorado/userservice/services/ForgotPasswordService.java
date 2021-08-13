package com.eldorado.userservice.services;

import org.springframework.http.ResponseEntity;

import com.eldorado.userservice.entities.AuthUser;

public interface ForgotPasswordService {
	public ResponseEntity<String> processForgotPassword(AuthUser user);
	public ResponseEntity<String> processResetPassword(AuthUser user, String newPassword);
	
}
