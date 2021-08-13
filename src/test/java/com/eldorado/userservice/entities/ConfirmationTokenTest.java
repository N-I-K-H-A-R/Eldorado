package com.eldorado.userservice.entities;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConfirmationTokenTest {

	ConfirmationToken token;

	/*
	 * Creating ConfirmationToken class Object before each test case
	 */

	@BeforeEach
	void setToken() {
		AuthUser user = new AuthUser();
		user.setId(1L);
		user.setFirstName("Sonal");
		user.setLastName("Kumar");
		user.setEmail("sonal@gmail.com");
		user.setPassword("Sonal@123");
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		Set<Role> roles = new HashSet<>();
		Role role = new Role();
		role.setId(1L);
		role.setName("USER");
		roles.add(role);
		user.setRoles(roles);
		user.setModifiedBy("me");
		user.setCreated(LocalDateTime.now());
		user.setCreatedBy("me");
		user.setModified(LocalDateTime.now());
		user.setEnabled(true);
		List<String> list = Arrays.asList("sup1", "sup2", "sup3");
		user.setLastThreePasswords(list);
		token = new ConfirmationToken(user);
		token.setTokenid(1L);
		token.setConfirmationToken("USER123");
		token.setCreatedDate(new Date());
		token.setUser(user);
	}

	@Test
	void testGetTokenid() {
		String expected = "USER123";
		String actual = token.getConfirmationToken();
		assertEquals(expected, actual);
	}

	@Test
	void testGetId() {
		long expected = 1L;
		long actual = token.getTokenid();
		assertEquals(expected, actual);
	}

	@Test
	void testGetDate() {
		Date actual = token.getCreatedDate();
		assertNotNull(actual);
	}

	@Test
	void testGetUser() {
		AuthUser actual = token.getUser();
		assertNotNull(actual);
	}

}
