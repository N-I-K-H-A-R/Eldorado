package com.eldorado.userservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eldorado.userservice.constants.UserConstants;
import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.operations.Mailer;
import com.eldorado.userservice.operations.PasswordChanger;
@ExtendWith(MockitoExtension.class)
class ChangePasswordServiceTest {
	@Mock
	private PasswordChanger passwordChanger;

	@InjectMocks
	private AuthUser user;

	@InjectMocks
	private ChangePasswordService changePasswordService;

	@Mock
	private Mailer mailSender;

	@Test
	void should_Return_Password_Changed() {
		// given
		user.setId(1L);
		user.setEmail("test_user@gmail.com");
		user.setPassword("testPassword@123");
		user.setEnabled(true);

		String newPassword = "newPassword@123";
		String message = UserConstants.PASS_CHANGED;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.OK).body(message);

		// while
		when(passwordChanger.changePassword(user, newPassword)).thenReturn(expected);
		ResponseEntity<String> observed = changePasswordService.processChangePassword(user, newPassword);

		// then
		assertEquals(expected, observed);
	}

}
