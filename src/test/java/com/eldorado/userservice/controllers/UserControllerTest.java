package com.eldorado.userservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.eldorado.userservice.dto.ChangePasswordDTO;
import com.eldorado.userservice.dto.NewPasswordDTO;
import com.eldorado.userservice.dto.UserDTO;
import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.filters.ChangePasswordFilter;
import com.eldorado.userservice.filters.ForgotPasswordFilterImpl;
import com.eldorado.userservice.filters.RegistrationFilter;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	@InjectMocks
	private UserController controller;
	@Mock
	private ForgotPasswordFilterImpl forgotPasswordFilter;
	@Mock
	private RegistrationFilter registrationFilter;
	@Mock
	private ChangePasswordFilter changePasswordFilter;
	@Mock
	private UserDTO userDto;
	@InjectMocks
	private NewPasswordDTO passDto;
	@InjectMocks
	private AuthUser user;
	@InjectMocks
	private ChangePasswordDTO changeDto;

	@BeforeEach
	void initialize_User_And_Token() {
		user.setId(1L);
		user.setEmail("test_user@gmail.com");
		user.setPassword("testPassword@123");
		user.setEnabled(true);
		passDto.setPassword("Hello@134");

	}

	@Test
	void test_demoCheck() {
		// given
		String expected = "I AM Healthy";
		// when
		String observed = controller.demoCheck();
		// then
		assertEquals(expected, observed);
	}

	@Test
	void test_registerUser() {
		// given
		ResponseEntity<String> expected = null;
		// when
		when(userDto.toEntity()).thenReturn(user);
		ResponseEntity<String> observed = controller.registerUser(userDto);
		// then
		assertEquals(expected, observed);
	}

	@Test
	void test_confirmUserAccount() {
		// given
		String confirmToken = UUID.randomUUID().toString();
		ResponseEntity<String> expected = null;
		// when
		ResponseEntity<String> observed = controller.confirmUserAccount(confirmToken);
		// then
		assertEquals(expected, observed);
	}

	@Test
	void test_confirmValidUser() {
		// given
		ResponseEntity<String> expected = null;
		// when
		ResponseEntity<String> observed = controller.confirmValidUser(user.getEmail());
		// then
		assertEquals(expected, observed);
	}

	@Test
	void test_processForgotPassword() {
		// given
		ResponseEntity<String> expected = null;
		// when
		ResponseEntity<String> observed = controller.processForgotPassword(user.getEmail());
		// then
		assertEquals(expected, observed);
	}

	@Test
	void test_showResetPasswordForm() {
		// given
		ResponseEntity<String> expected = null;
		// when
		ResponseEntity<String> observed = controller.showResetPasswordForm(user.getPassword());
		// then
		assertEquals(expected, observed);
	}

	@Test
	void test_processResetPassword() {
		// given
		String confirmToken = UUID.randomUUID().toString();
		ResponseEntity<String> expected = null;
		// when
		ResponseEntity<String> observed = controller.processResetPassword(passDto, confirmToken);
		// then
		assertEquals(expected, observed);
	}

	@Test
	void test_changePassword() {
		// given
		changeDto.setEmail("test_user@gmail.com");
		changeDto.setCurrentPassword("testPassword@123");
		changeDto.setNewPassword("Hello@123");
		ResponseEntity<String> expected = null;
		// when
		ResponseEntity<String> observed = controller.changePassword(changeDto);
		// then
		assertEquals(expected, observed);
	}
}
