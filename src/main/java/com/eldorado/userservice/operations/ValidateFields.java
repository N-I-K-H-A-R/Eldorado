package com.eldorado.userservice.operations;

import java.util.regex.*;

import org.springframework.stereotype.Service;

import com.eldorado.userservice.constants.UserConstants;
import com.eldorado.userservice.exceptions.EmptyFieldException;
import com.eldorado.userservice.exceptions.InvalidDataException;

/**
 * ValidateFields Class for Handling Confirmation Token Already Used
 */
@Service
public class ValidateFields {
	private ValidateFields() {
	}

	/**
	 * Validate UserName against user name policy
	 * 
	 * @param username
	 * @return boolean
	 */
	public boolean validateUsername(String username) throws EmptyFieldException, InvalidDataException {

		String regex = UserConstants.USER_REGEX;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(username);

		if (username == null || username.equals(""))
			throw new EmptyFieldException(UserConstants.USERNAME_CANNOT_BE_EMPTY);
		if (!matcher.matches())
			throw new InvalidDataException(UserConstants.USERNAME_DESCRIPTION);

		return true;
	}

	/**
	 * Validate password against password policy
	 * 
	 * @param password
	 * @return boolean
	 */
	public boolean validatePassword(String password) throws EmptyFieldException, InvalidDataException {
		String regex = UserConstants.PASS_PATTERN;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);

		if (password == null || password.equals(""))
			throw new EmptyFieldException(UserConstants.PASS_CANNOT_BE_EMPTY);
		if (!matcher.matches())
			throw new InvalidDataException(UserConstants.PASS_DESCRIPTION);

		return true;
	}

}