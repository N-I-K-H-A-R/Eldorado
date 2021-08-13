package com.eldorado.userservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eldorado.userservice.constants.UserConstants;
import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.entities.ConfirmationToken;
import com.eldorado.userservice.entities.Role;
import com.eldorado.userservice.factories.ConfirmTokenFactory;
import com.eldorado.userservice.operations.Mailer;
import com.eldorado.userservice.repositories.ConfirmationTokenRepository;
import com.eldorado.userservice.repositories.RoleRepository;
import com.eldorado.userservice.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

	@InjectMocks
	RegistrationService registrationService;

	@Mock
	UserRepository userRepository;

	@Mock
	RoleRepository roleRepository;

	@Mock
	private ConfirmTokenFactory tokenFactory;
	@Mock
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Mock
	private Mailer mailSender;

	@InjectMocks
	private AuthUser user;

	@InjectMocks
	private Role role;

	@InjectMocks
	private ConfirmationToken confirmationToken;

	@Mock
	BCryptPasswordEncoder passwordEncoder;

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
	void should_Register_when_User_Not_Done_Registration() {
		// given
		String message = UserConstants.EMAIL_SENT_SUCCESSFULLY;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.OK).body(message);

		// when
		when(roleRepository.findByName(UserConstants.ROLE_USER)).thenReturn(Optional.of(role));
		when(tokenFactory.createConfirmationToken(user)).thenReturn(confirmationToken);
		when(userRepository.save(user)).thenReturn(user);
		when(tokenFactory.createConfirmationToken(user)).thenReturn(confirmationToken);
		when(confirmationTokenRepository.save(confirmationToken)).thenReturn(confirmationToken);
		when(mailSender.sendVerificationEmail(user, confirmationToken)).thenReturn(true);
		ResponseEntity<String> observed = registrationService.registerUser(user);
		// then
		assertEquals(expected, observed);
	}

	@Test
	void verify_user_account() {
		// given
		String message = UserConstants.USER_REGISTERED_SUCCESSFULLY;
		ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.OK).body(message);
		// when
		when(userRepository.save(user)).thenReturn(user);
		ResponseEntity<String> observed = registrationService.verifyUserAccount(user);
		// then
		assertEquals(expected, observed);
	}
}
