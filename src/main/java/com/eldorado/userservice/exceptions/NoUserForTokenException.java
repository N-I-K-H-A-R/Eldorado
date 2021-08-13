package com.eldorado.userservice.exceptions;

/**
 * Exception Class for Handling Token found but not User
 */
public class NoUserForTokenException extends Exception {

	private static final long serialVersionUID = 3526527065568908556L;

	public NoUserForTokenException(String message) {
		super(message);
	}
}
