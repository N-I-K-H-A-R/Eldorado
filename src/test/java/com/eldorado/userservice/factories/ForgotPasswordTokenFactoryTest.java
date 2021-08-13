package com.eldorado.userservice.factories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.entities.ForgotPasswordToken;

@ExtendWith(MockitoExtension.class)
class ForgotPasswordTokenFactoryTest {

	@InjectMocks
	private AuthUser user;
	@InjectMocks
	private ForgotPasswordTokenFactory forgotPasswordTokenFactory;

	@BeforeEach
	void initialize() {
		user = new AuthUser();
		user.setId(1L);
		user.setEmail("test_user@gmail.com");
		user.setPassword("testPassword@123");
		user.setEnabled(false);

	}

	@Test
	void test() {

		// when
		ForgotPasswordToken tokenObserved = forgotPasswordTokenFactory.createForgotPasswordToken(user);
		// then
		assertTrue(tokenObserved instanceof ForgotPasswordToken);
	}

}
