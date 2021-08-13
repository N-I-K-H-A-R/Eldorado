package com.eldorado.userservice.factories;

import org.springframework.stereotype.Service;

import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.entities.ForgotPasswordToken;

//component static
@Service
public class ForgotPasswordTokenFactory {
	public ForgotPasswordToken createForgotPasswordToken(AuthUser user) {
		return new ForgotPasswordToken(user);
	}
}
