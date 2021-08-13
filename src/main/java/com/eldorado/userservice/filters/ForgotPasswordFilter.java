package com.eldorado.userservice.filters;

import org.springframework.http.ResponseEntity;

public interface ForgotPasswordFilter {
	public ResponseEntity<String> processForgotPasswordFilter(String email);
	public ResponseEntity<String> showResetPasswordFormFilter(String forgotPasswordToken);
	public ResponseEntity<String> processResetPasswordFilter(String forgotPasswordToken, String newPassword);
}
