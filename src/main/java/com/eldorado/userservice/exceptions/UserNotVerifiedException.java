package com.eldorado.userservice.exceptions;

/**
 * Exception Class for Handling User Not Verified
 */
public class UserNotVerifiedException extends Exception {

	private static final long serialVersionUID = -4060824097955753355L;

	public UserNotVerifiedException(String message) {
		super(message);
	}
}
