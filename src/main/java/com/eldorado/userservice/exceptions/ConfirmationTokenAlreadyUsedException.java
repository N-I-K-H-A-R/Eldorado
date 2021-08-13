package com.eldorado.userservice.exceptions;

/**
 * Exception Class for Handling Confirmation Token Already Used
 */
public class ConfirmationTokenAlreadyUsedException extends Exception {

	private static final long serialVersionUID = -3121502957564170763L;

	public ConfirmationTokenAlreadyUsedException(String message) {
		super(message);
	}

}
