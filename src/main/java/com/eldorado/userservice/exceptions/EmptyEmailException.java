package com.eldorado.userservice.exceptions;

/**
 * Exception Class for Null Email Body
 */
public class EmptyEmailException extends Exception {

	private static final long serialVersionUID = -2215818311149150954L;

	public EmptyEmailException(String message) {
		super(message);
	}
}
