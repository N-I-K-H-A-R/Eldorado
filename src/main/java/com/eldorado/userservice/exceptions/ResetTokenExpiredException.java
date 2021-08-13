package com.eldorado.userservice.exceptions;

/**
 * Exception Class for Handling Reset Token Expired
 */
public class ResetTokenExpiredException extends Exception {

	private static final long serialVersionUID = 1763707219525160073L;

	public ResetTokenExpiredException(String message) {
		super(message);
	}

}
