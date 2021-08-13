package com.eldorado.userservice.factories;

import org.springframework.stereotype.Service;

import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.entities.ConfirmationToken;

@Service
public class ConfirmTokenFactory {
	public ConfirmationToken createConfirmationToken(AuthUser user) {
		return new ConfirmationToken(user);
	}
}
