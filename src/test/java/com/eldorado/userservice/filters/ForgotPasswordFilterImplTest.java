package com.eldorado.userservice.filters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eldorado.userservice.constants.UserConstants;
import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.entities.ForgotPasswordToken;
import com.eldorado.userservice.exceptions.EmptyFieldException;
import com.eldorado.userservice.exceptions.InvalidDataException;
import com.eldorado.userservice.operations.ValidateFields;
import com.eldorado.userservice.repositories.ForgotPasswordTokenRepository;
import com.eldorado.userservice.repositories.UserRepository;
import com.eldorado.userservice.services.ForgotPasswordServiceImpl;

@ExtendWith(MockitoExtension.class)
class ForgotPasswordFilterImplTest {

	@InjectMocks
	private ForgotPasswordFilterImpl forgotPasswordFilter;

	@Mock
	private ValidateFields validateFields;

	@Mock
	private UserRepository userRepository;

	@Mock
	private ForgotPasswordServiceImpl forgotPasswordService;

	@Mock
	private ForgotPasswordTokenRepository forgotPasswordTokenRepository;

	@InjectMocks
	private AuthUser user;

	@InjectMocks
	private ForgotPasswordToken forgotPasswordToken;

	@Mock
	private Environment environment;

	private static final Integer validDuration = 120;

	@BeforeEach
	void initialize_User_And_Token() {
		user.setId(1L);
		user.setEmail("test_user@gmail.com");
		user.setPassword("testPassword@123");
		user.setEnabled(true);

		forgotPasswordToken.setTokenid(2L);
		forgotPasswordToken.setUser(user);
		forgotPasswordToken.setCreatedDateTime(LocalDateTime.now());
		forgotPasswordToken.setForgotPassToken(UUID.randomUUID().toString());
	}

	@Test
	void should_Return_USERNAME_CANNOT_BE_EMPTY() throws EmptyFieldException, InvalidDataException {
		// given
		String message = "emptyException";
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		String email = "";

		// when
		when(validateFields.validateUsername(email)).thenThrow(EmptyFieldException.class);
		ResponseEntity<String> observed = forgotPasswordFilter.processForgotPasswordFilter(email);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_INVALID_EMAIL_FORMAT() throws EmptyFieldException, InvalidDataException {
		// given
		String email = "invalid_email";
		String message = UserConstants.INVALID_EMAIL_FORMAT;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(validateFields.validateUsername(email)).thenReturn(false);
		ResponseEntity<String> observed = forgotPasswordFilter.processForgotPasswordFilter(email);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_NO_USER_FOUND() throws EmptyFieldException, InvalidDataException {
		// given
		String email = "unregistered_user@gmail.com";
		String message = UserConstants.NO_USER_FOUND;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(validateFields.validateUsername(email)).thenReturn(true);
		when(userRepository.findByEmailIgnoreCase(email)).thenReturn(null);
		ResponseEntity<String> observed = forgotPasswordFilter.processForgotPasswordFilter(email);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_NOT_VERIFIED() throws EmptyFieldException, InvalidDataException {
		// given
		user.setEnabled(false);
		String email = "unverified_user@gmail.com";
		String message = UserConstants.NOT_VERIFIED;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(validateFields.validateUsername(email)).thenReturn(true);
		when(userRepository.findByEmailIgnoreCase(email)).thenReturn(user);

		ResponseEntity<String> observed = forgotPasswordFilter.processForgotPasswordFilter(email);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_PASS_RECOVERY_EMAIL_SENT() throws EmptyFieldException, InvalidDataException {
		// give
		user.setEnabled(true);
		String email = "abhijeet.srivastava6499@gmail.com";
		String message = UserConstants.INVALID_EMAIL_FORMAT;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(validateFields.validateUsername(email)).thenReturn(true);
		when(userRepository.findByEmailIgnoreCase(email)).thenReturn(user);
		when(forgotPasswordService.processForgotPassword(user)).thenReturn(expected);

		ResponseEntity<String> observed = forgotPasswordFilter.processForgotPasswordFilter(email);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_INVALID_TOKEN() {
		// given
		forgotPasswordToken = null;
		String token = "Invalid_Token";
		String message = UserConstants.INVALID_TOKEN;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(forgotPasswordTokenRepository.findByForgotPassToken(token)).thenReturn(forgotPasswordToken);
		ResponseEntity<String> observed = forgotPasswordFilter.showResetPasswordFormFilter(token);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_TOKEN_EXPIRED() {
		// given
		forgotPasswordToken.setCreatedDateTime(LocalDateTime.now().minusSeconds(validDuration));
		String token = "Expired_Token";
		String message = UserConstants.TOKEN_EXPIRED;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(forgotPasswordTokenRepository.findByForgotPassToken(token)).thenReturn(forgotPasswordToken);
		ResponseEntity<String> observed = forgotPasswordFilter.showResetPasswordFormFilter(token);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_VALID_TOKEN() {
		// given
		String token = "Valid_Token";
		String message = UserConstants.VALID_TOKEN;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.OK).body(message);

		// when
		when(forgotPasswordTokenRepository.findByForgotPassToken(token)).thenReturn(forgotPasswordToken);
		ResponseEntity<String> observed = forgotPasswordFilter.showResetPasswordFormFilter(token);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_INVALID_TOKEN_From_processResetPasswordFilter() {
		// given
		forgotPasswordToken = null;
		String token = "Invalid_Token";
		String newPassword = "newPassword@123";
		String message = UserConstants.INVALID_TOKEN;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(forgotPasswordTokenRepository.findByForgotPassToken(token)).thenReturn(forgotPasswordToken);
		ResponseEntity<String> observed = forgotPasswordFilter.processResetPasswordFilter(token, newPassword);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_PASS_CANNOT_BE_EMPTY_From_processResetPasswordFilter()
			throws EmptyFieldException, InvalidDataException {
		// given
		String token = "Valid_Token";
		String emptyPassword = "";

		String message = UserConstants.PASS_CANNOT_BE_EMPTY;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(forgotPasswordTokenRepository.findByForgotPassToken(token)).thenReturn(forgotPasswordToken);
		when(validateFields.validatePassword(emptyPassword)).thenThrow(EmptyFieldException.class);

		ResponseEntity<String> observed = forgotPasswordFilter.processResetPasswordFilter(token, emptyPassword);

		// then
		assertEquals(expected, observed);
	}
	@Test
	void should_Return_PASS_CANNOT_BE_Invalid_From_processResetPasswordFilter()
			throws EmptyFieldException, InvalidDataException {
		// given
		String token = "Valid_Token";
		String emptyPassword = "hello";

		String message = UserConstants.PASS_DESCRIPTION;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(forgotPasswordTokenRepository.findByForgotPassToken(token)).thenReturn(forgotPasswordToken);
		when(validateFields.validatePassword(emptyPassword)).thenThrow(InvalidDataException.class);

		ResponseEntity<String> observed = forgotPasswordFilter.processResetPasswordFilter(token, emptyPassword);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_INVALID_PASS_FORMAT_From_processResetPasswordFilter()
			throws EmptyFieldException, InvalidDataException {
		// given
		String token = "Valid_Token";
		String newPassword = "newPassword@123";
		String message = UserConstants.INVALID_PASS_FORMAT;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(forgotPasswordTokenRepository.findByForgotPassToken(token)).thenReturn(forgotPasswordToken);
		when(validateFields.validatePassword(newPassword)).thenReturn(false);

		ResponseEntity<String> observed = forgotPasswordFilter.processResetPasswordFilter(token, newPassword);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_PASS_CHANGED_From_processResetPasswordFilter() throws EmptyFieldException, InvalidDataException {
		// given
		String token = "Valid_Token";
		String newPassword = "newPassword@123";
		String message = UserConstants.PASS_CHANGED;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.OK).body(message);

		// when
		when(forgotPasswordTokenRepository.findByForgotPassToken(token)).thenReturn(forgotPasswordToken);
		when(validateFields.validatePassword(newPassword)).thenReturn(true);
		when(forgotPasswordService.processResetPassword(user, newPassword)).thenReturn(expected);

		ResponseEntity<String> observed = forgotPasswordFilter.processResetPasswordFilter(token, newPassword);

		// then
		assertEquals(expected, observed);
	}

}