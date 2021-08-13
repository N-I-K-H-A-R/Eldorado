package com.eldorado.userservice.operations;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.eldorado.userservice.entities.ConfirmationToken;
import com.eldorado.userservice.entities.Role;
import com.eldorado.userservice.services.EmailService;

@ExtendWith(MockitoExtension.class)
class PasswordChangerTest {

	@InjectMocks
	private AuthUser user;
	@InjectMocks
	private PasswordChanger passwordChanger;
	@InjectMocks
	private Role role;
	@InjectMocks
	private Mailer mailer;
	@InjectMocks
	private ConfirmationToken confirmationToken;

	@Mock
	private EmailService emailService;
	@Mock
	BCryptPasswordEncoder passwordEncoder;
	@Mock
	private Environment environment;

	@BeforeEach
	void initialize_User() {
		user = new AuthUser();

		user.setId(1L);
		user.setEmail("test_user@gmail.com");
		user.setPassword("testPassword@123");
	}

	@Test
	void should_Return_No_User_Found() {
		// given
		String message = UserConstants.NO_USER_FOUND;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		user = null;
		String newPassword = "Hello@123";
		// when
		ResponseEntity<String> observed = passwordChanger.changePassword(user, newPassword);
		// then
		assertEquals(expected, observed);
	}

}
