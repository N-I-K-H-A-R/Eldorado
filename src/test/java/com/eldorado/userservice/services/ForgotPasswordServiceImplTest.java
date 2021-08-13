package com.eldorado.userservice.services;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eldorado.userservice.constants.UserConstants;
import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.entities.ForgotPasswordToken;
import com.eldorado.userservice.factories.ForgotPasswordTokenFactory;
import com.eldorado.userservice.operations.Mailer;
import com.eldorado.userservice.operations.PasswordChanger;
import com.eldorado.userservice.repositories.ForgotPasswordTokenRepository;

@ExtendWith(MockitoExtension.class)
class ForgotPasswordServiceImplTest {

	@InjectMocks
	private ForgotPasswordServiceImpl forgotPasswordService;

	@Mock
	private ForgotPasswordTokenFactory tokenFactory;

	@Mock
	private ForgotPasswordTokenRepository forgotPasswordTokenRepository;

	@Mock
	private Mailer mailSender;

	@Mock
	private PasswordChanger passwordChanger;

	@InjectMocks
	private AuthUser user;

	@InjectMocks
	private ForgotPasswordToken forgotPasswordToken;

	public @Value("${validduration}") Integer validDuration;

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
	void should_Return_PASS_RECOVERY_EMAIL_SENT() {

		// given
		String message = UserConstants.PASS_RECOVERY_EMAIL_SENT;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.OK).body(message);

		// when
		when(tokenFactory.createForgotPasswordToken(user)).thenReturn(forgotPasswordToken);
		when(forgotPasswordTokenRepository.save(forgotPasswordToken)).thenReturn(forgotPasswordToken);
		when(mailSender.sendPasswordRecoveryEmail(user, forgotPasswordToken)).thenReturn(true);

		ResponseEntity<String> observed = forgotPasswordService.processForgotPassword(user);

		// then
		assertEquals(expected, observed);
	}

	@Test
	void should_Return_PASS_CHANGED() {
		// given
		String newPassword = "newPassword@123";
		String message = UserConstants.PASS_CHANGED;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.OK).body(message);

		// while
		when(passwordChanger.changePassword(user, newPassword)).thenReturn(expected);
		ResponseEntity<String> observed = forgotPasswordService.processResetPassword(user, newPassword);

		// then
		assertEquals(expected, observed);
	}

}
