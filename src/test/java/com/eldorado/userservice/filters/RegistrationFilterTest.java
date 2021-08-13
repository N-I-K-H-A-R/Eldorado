package com.eldorado.userservice.filters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eldorado.userservice.constants.UserConstants;
import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.entities.ConfirmationToken;
import com.eldorado.userservice.entities.Role;
import com.eldorado.userservice.exceptions.EmptyFieldException;
import com.eldorado.userservice.exceptions.InvalidDataException;
import com.eldorado.userservice.operations.ValidateFields;
import com.eldorado.userservice.repositories.ConfirmationTokenRepository;
import com.eldorado.userservice.repositories.UserRepository;
import com.eldorado.userservice.services.RegistrationService;

@ExtendWith(MockitoExtension.class)
class RegistrationFilterTest {

	@InjectMocks
	private AuthUser user;

	@InjectMocks
	private Role role;

	@InjectMocks
	private ConfirmationToken confirmationToken;
	@InjectMocks
	private RegistrationFilter registrationFilter;
	@Mock
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Mock
	private UserRepository userRepository;
	@Mock
	private RegistrationService registrationService;
	@Mock
	private ValidateFields validateFields;

	@BeforeEach
	void initialize_User_And_Token() {
		user = new AuthUser();
		user.setId(1L);
		user.setEmail("test_user@gmail.com");
		user.setPassword("testPassword@123");
		user.setEnabled(false);
		confirmationToken = new ConfirmationToken(user);
		confirmationToken.setTokenid(2L);
		confirmationToken.setUser(user);
		confirmationToken.setCreatedDate(new Date());
		confirmationToken.setConfirmationToken(UUID.randomUUID().toString());
		role.setId(1L);
		role.setName("ROLE_USER");
	}

	@Test
	void should_verifyUserAccountFilter_when_token_empty() {
		// given
		String token = "";
		String message = UserConstants.TOKEN_CANNOT_BE_EMPTY;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when

		ResponseEntity<String> observed = registrationFilter.verifyUserAccountFilter(token);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_verifyUserAccountFilter_when_token_isNotNull() {
		// given
		String token = UUID.randomUUID().toString();
		String message = UserConstants.TOKEN_ALREADY_USED;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(confirmationTokenRepository.findByConfirmedToken(token)).thenReturn(confirmationToken);
		user.setEnabled(true);
		when(userRepository.findByEmailIgnoreCase(confirmationToken.getUser().getEmail())).thenReturn(user);
		ResponseEntity<String> observed = registrationFilter.verifyUserAccountFilter(token);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_verifyUserAccountFilter_when_token_isNotNull_enable_False() {
		// given
		String token = UUID.randomUUID().toString();
		String message = UserConstants.USER_REGISTERED_SUCCESSFULLY;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.OK).body(message);

		// when
		when(confirmationTokenRepository.findByConfirmedToken(token)).thenReturn(confirmationToken);
		when(userRepository.findByEmailIgnoreCase(confirmationToken.getUser().getEmail())).thenReturn(user);
		when(registrationService.verifyUserAccount(user)).thenReturn(expected);
		ResponseEntity<String> observed = registrationFilter.verifyUserAccountFilter(token);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_verifyUserAccountFilter_when_token_isNull() {
		// given
		String token = UUID.randomUUID().toString();
		String message = UserConstants.INVALID_TOKEN;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(confirmationTokenRepository.findByConfirmedToken(token)).thenReturn(null);
		ResponseEntity<String> observed = registrationFilter.verifyUserAccountFilter(token);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_confirmValidUserFilter_user_isNull() {
		// given
		String username = "abc@gmail.com";
		String message = UserConstants.NO_USER_FOUND;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(userRepository.findByEmailIgnoreCase(username)).thenReturn(null);
		ResponseEntity<String> observed = registrationFilter.confirmValidUserFilter(username);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_confirmValidUserFilter_user_isNotNull_And_enableTrue() {
		// given
		String username = "abc@gmail.com";
		String message = UserConstants.USER_VERIFIED;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.OK).body(message);

		// when
		when(userRepository.findByEmailIgnoreCase(username)).thenReturn(user);
		user.setEnabled(true);
		ResponseEntity<String> observed = registrationFilter.confirmValidUserFilter(username);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_confirmValidUserFilter_user_isNotNull_And_enableFalse() {
		// given
		String username = "abc@gmail.com";
		String message = UserConstants.NOT_VERIFIED;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.OK).body(message);

		// when
		when(userRepository.findByEmailIgnoreCase(username)).thenReturn(user);
		user.setEnabled(false);
		ResponseEntity<String> observed = registrationFilter.confirmValidUserFilter(username);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_registerUserFilter_ifTrue_andUserNotEnabled() throws EmptyFieldException, InvalidDataException {
		// given
		String message = UserConstants.VERIFICATION_EMAIL_SENT;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(validateFields.validateUsername(user.getEmail())).thenReturn(true);
		when(validateFields.validatePassword(user.getPassword())).thenReturn(true);
		when(userRepository.findByEmailIgnoreCase(user.getEmail())).thenReturn(user);
		user.setEnabled(false);
		ResponseEntity<String> observed = registrationFilter.registerUserFilter(user);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_registerUserFilter_ifTrue_andUserEnabled() throws EmptyFieldException, InvalidDataException {
		// given
		String message = UserConstants.USER_ALREADY_EXIST_EXCEPTION + user.getEmail() + UserConstants.PLEASE_LOGIN;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(validateFields.validateUsername(user.getEmail())).thenReturn(true);
		when(validateFields.validatePassword(user.getPassword())).thenReturn(true);
		when(userRepository.findByEmailIgnoreCase(user.getEmail())).thenReturn(user);
		user.setEnabled(true);
		ResponseEntity<String> observed = registrationFilter.registerUserFilter(user);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_return_Username_Cannot_Be_Empty() throws EmptyFieldException, InvalidDataException {
		// given
		user.setEmail("");
		String message = UserConstants.USERNAME_CANNOT_BE_EMPTY;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(validateFields.validateUsername(user.getEmail())).thenThrow(EmptyFieldException.class);
		ResponseEntity<String> observed = registrationFilter.registerUserFilter(user);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_return_Password_Cannot_Be_Empty() throws EmptyFieldException, InvalidDataException {
		// given
		user.setPassword("");
		String message = UserConstants.PASS_CANNOT_BE_EMPTY;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(validateFields.validateUsername(user.getEmail())).thenReturn(true);
		when(validateFields.validatePassword(user.getPassword())).thenThrow(EmptyFieldException.class);
		ResponseEntity<String> observed = registrationFilter.registerUserFilter(user);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_return_Password_Should_Follow_Policies() throws EmptyFieldException, InvalidDataException {
		// given
		user.setPassword("hell1");
		String message = UserConstants.PASS_DESCRIPTION;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(validateFields.validateUsername(user.getEmail())).thenReturn(true);
		when(validateFields.validatePassword(user.getPassword())).thenThrow(InvalidDataException.class);
		ResponseEntity<String> observed = registrationFilter.registerUserFilter(user);

		// then
		assertEquals(expected, observed);
	}
}
