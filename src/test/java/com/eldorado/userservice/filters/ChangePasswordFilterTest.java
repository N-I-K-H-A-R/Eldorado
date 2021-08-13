package com.eldorado.userservice.filters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eldorado.userservice.constants.UserConstants;
import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.entities.ForgotPasswordToken;
import com.eldorado.userservice.exceptions.EmptyFieldException;
import com.eldorado.userservice.exceptions.InvalidDataException;
import com.eldorado.userservice.operations.PasswordChanger;
import com.eldorado.userservice.operations.ValidateFields;
import com.eldorado.userservice.repositories.ForgotPasswordTokenRepository;
import com.eldorado.userservice.repositories.UserRepository;
import com.eldorado.userservice.services.ChangePasswordService;

@ExtendWith(MockitoExtension.class)
class ChangePasswordFilterTest {

	@InjectMocks
	private ChangePasswordFilter changePasswordFilter;

	@Mock
	private ValidateFields validateFields;

	@Mock
	private UserRepository userRepository;

	@Mock
	private ChangePasswordService changePasswordService;

	@Mock
	private ForgotPasswordTokenRepository forgotPasswordTokenRepository;

	@InjectMocks
	private AuthUser user;

	@Mock
	BCryptPasswordEncoder passwordEncoder;

	@Mock
	private PasswordChanger passwordChanger;

	@InjectMocks
	private ForgotPasswordToken forgotPasswordToken;

	@Mock
	private Environment environment;

	@BeforeEach
	void initialize_User_And_Token() {
		user.setId(1L);
		user.setEmail("test_user@gmail.com");
		user.setPassword("testPassword@123");
		user.setEnabled(true);
	}

	@Test
	void should_Return_Username_Cannot_Be_Empty() throws EmptyFieldException, InvalidDataException {
		// given
		String message = UserConstants.NO_USER_FOUND;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		String email = "";
		String currentPassword = "Test@123";
		String newPassword = "Hello@123";
		// when

		ResponseEntity<String> observed = changePasswordFilter.processChangePasswordFilter(email, currentPassword,
				newPassword);
		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_No_User_Found() throws EmptyFieldException, InvalidDataException {
		// given
		String email = "unregistered_user@gmail.com";
		String currentPassword = "Test@123";
		String newPassword = "Hello@123";
		String message = UserConstants.NO_USER_FOUND;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(userRepository.findByEmailIgnoreCase(email)).thenReturn(null);
		ResponseEntity<String> observed = changePasswordFilter.processChangePasswordFilter(email, currentPassword,
				newPassword);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_Invalid_Current_Password() throws EmptyFieldException, InvalidDataException {
		// given
		String email = "unregistered_user@gmail.com";
		String currentPassword = "Test@123";
		String newPassword = "Hello@123";
		String message = UserConstants.INVALID_PASS;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(userRepository.findByEmailIgnoreCase(email)).thenReturn(user);
		when(passwordEncoder.matches(currentPassword, user.getPassword())).thenReturn(false);
		ResponseEntity<String> observed = changePasswordFilter.processChangePasswordFilter(email, currentPassword,
				newPassword);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_Invalid_Password_Format_From_processChangePasswordFilter()
			throws EmptyFieldException, InvalidDataException {
		// given
		String email = "unregistered_user@gmail.com";
		String currentPassword = "testPassword@123";
		String newPassword = "hello123";
		String message = UserConstants.INVALID_PASS_FORMAT;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(userRepository.findByEmailIgnoreCase(email)).thenReturn(user);
		when(passwordEncoder.matches(currentPassword, user.getPassword())).thenReturn(true);
		when(validateFields.validatePassword(newPassword)).thenReturn(false);
		ResponseEntity<String> observed = changePasswordFilter.processChangePasswordFilter(email, currentPassword,
				newPassword);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_Password_Changed_From_processChangePasswordFilter()
			throws EmptyFieldException, InvalidDataException {
		// given
		String email = "unregistered_user@gmail.com";
		String currentPassword = "testPassword@123";
		String newPassword = "Hello@123";
		String message = UserConstants.PASS_CHANGED;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(userRepository.findByEmailIgnoreCase(email)).thenReturn(user);
		when(passwordEncoder.matches(currentPassword, user.getPassword())).thenReturn(true);
		when(validateFields.validatePassword(newPassword)).thenReturn(true);
		when(changePasswordService.processChangePassword(user, newPassword)).thenReturn(expected);
		ResponseEntity<String> observed = changePasswordFilter.processChangePasswordFilter(email, currentPassword,
				newPassword);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_Empty_Field_Exception_From_processChangePasswordFilter()
			throws EmptyFieldException, InvalidDataException {
		// given
		String email = "unregistered_user@gmail.com";
		String currentPassword = "testPassword@123";
		String newPassword = "";
		String message = UserConstants.PASS_CANNOT_BE_EMPTY;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(userRepository.findByEmailIgnoreCase(email)).thenReturn(user);
		when(passwordEncoder.matches(currentPassword, user.getPassword())).thenReturn(true);
		when(validateFields.validatePassword(newPassword)).thenThrow(EmptyFieldException.class);
		ResponseEntity<String> observed = changePasswordFilter.processChangePasswordFilter(email, currentPassword,
				newPassword);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_Invalid_Data_Exception_From_processChangePasswordFilter()
			throws EmptyFieldException, InvalidDataException {
		// given
		String email = "unregistered_user@gmail.com";
		String currentPassword = "testPassword@123";
		String newPassword = "hi123456";
		String message = UserConstants.PASS_DESCRIPTION;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

		// when
		when(userRepository.findByEmailIgnoreCase(email)).thenReturn(user);
		when(passwordEncoder.matches(currentPassword, user.getPassword())).thenReturn(true);
		when(validateFields.validatePassword(newPassword)).thenThrow(InvalidDataException.class);
		ResponseEntity<String> observed = changePasswordFilter.processChangePasswordFilter(email, currentPassword,
				newPassword);

		// then
		assertEquals(expected, observed);
	}

}
