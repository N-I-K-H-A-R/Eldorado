package com.eldorado.userservice.operations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.eldorado.userservice.exceptions.EmptyFieldException;
import com.eldorado.userservice.exceptions.InvalidDataException;

@ExtendWith(MockitoExtension.class)
class ValidationFieldsTest {

	@InjectMocks
	private ValidateFields validateFields;

	@Test
	void usernameEmpty() {
		Executable executable = () -> validateFields.validateUsername("");
		assertThrows(EmptyFieldException.class, executable);
	}

	@Test
	void passwordEmpty() {
		Executable executable = () -> validateFields.validatePassword("");
		assertThrows(EmptyFieldException.class, executable);
	}

	@Test
	void usernameCharactersNotAllowed() {
		Executable executable = () -> validateFields.validateUsername("ab#c@gmail.com");
		assertThrows(InvalidDataException.class, executable);
	}

	@Test
	void usernameInitialCharactersMissing() {
		Executable executable = () -> validateFields.validateUsername("@gmail.com");
		assertThrows(InvalidDataException.class, executable);
	}

	@Test
	void usernameCorrect() throws EmptyFieldException, InvalidDataException {
		try {
			assertTrue(validateFields.validateUsername("abc@gmail.com"));
		} catch (EmptyFieldException e) {
			System.out.println("Empty field");
		} catch (InvalidDataException e) {
			System.out.println("Invalid field");
		}
	}

	@ParameterizedTest
	@ValueSource(strings = { "Aojo@", "0123456789$abcdefgAB123456789", "java REGEX 123" })
	void should_Throw_InvalidDataException_When_passwordTooShortOrTooLongOrSpecialCharacterMissing(String password) {
		Executable executable = () -> validateFields.validatePassword(password);
		assertThrows(InvalidDataException.class, executable);
	}

	@Test
	void passwordCorrect() throws EmptyFieldException, InvalidDataException {
		try {
			assertTrue(validateFields.validatePassword("abc@123A23"));
		} catch (EmptyFieldException e) {
			System.out.println("Empty field");
		} catch (InvalidDataException e) {
			System.out.println("Invalid field");
		}
	}
}
