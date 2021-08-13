package com.eldorado.userservice.exceptions;

/**
 * Exception Class for Handling Invalid Data Exception
 */
public class InvalidDataException extends Exception {

	private static final long serialVersionUID = -1466868787095139869L;

	public InvalidDataException(String message) {
		super(message);
	}
}
