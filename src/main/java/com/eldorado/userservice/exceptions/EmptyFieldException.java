package com.eldorado.userservice.exceptions;

/**
 * Exception Class for Handling Empty Field
 */
public class EmptyFieldException extends Exception {

	private static final long serialVersionUID = -3840271438398743069L;

	public EmptyFieldException(String message) {
		super(message);
	}
}
