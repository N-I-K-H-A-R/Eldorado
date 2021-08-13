package com.eldorado.userservice.exceptions;

public class MailNotSendException extends Exception {
	private static final long serialVersionUID = 3526527065568908556L;

	public MailNotSendException(String message) {
		super(message);
	}
}
