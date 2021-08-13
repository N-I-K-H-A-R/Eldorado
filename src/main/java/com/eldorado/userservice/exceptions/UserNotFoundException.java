package com.eldorado.userservice.exceptions;

/**
 * Exception Class for Handling Confirmation User Not Found
 */
public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 6149560844650335075L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
