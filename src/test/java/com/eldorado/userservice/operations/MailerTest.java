package com.eldorado.userservice.operations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.entities.ConfirmationToken;
import com.eldorado.userservice.entities.ForgotPasswordToken;
import com.eldorado.userservice.entities.Role;
import com.eldorado.userservice.services.EmailService;

@ExtendWith(MockitoExtension.class)
class MailerTest {
	@InjectMocks
	private AuthUser user;

	@InjectMocks
	private Role role;
	@InjectMocks
	private Mailer mailer;
	@InjectMocks
	private ConfirmationToken confirmationToken;
	private ForgotPasswordToken tok;
	@Mock
	private EmailService emailService;

	@Mock
	private Environment environment;

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
		tok = new ForgotPasswordToken();
		tok.setTokenid(1L);
		tok.setForgotPassToken("TestToken@123");
		tok.setCreatedDateTime(LocalDateTime.now());
		tok.setUser(null);
	}

	@Test
	void test_sendVerificationEmail_true() {
		// given

		boolean expected = true;
		// when

		boolean observed = mailer.sendVerificationEmail(user, confirmationToken);
		// then
		assertEquals(expected, observed);
	}

	@Test
	void test_sendPasswordRecoveryEmail() {
		// given

		boolean expected = true;
		// when

		boolean observed = mailer.sendPasswordRecoveryEmail(user, tok);
		// then
		assertEquals(expected, observed);
	}
}
