package com.eldorado.userservice.exceptions;

/**
 * Exception Class for Handling User Already Exist
 */
public class UserAlreadyExistException extends Exception {

	private static final long serialVersionUID = 2392102222948317799L;

	public UserAlreadyExistException(String message) {
		super(message);
	}
}
