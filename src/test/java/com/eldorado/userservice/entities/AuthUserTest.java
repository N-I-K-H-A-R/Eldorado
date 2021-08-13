package com.eldorado.userservice.entities;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AuthUserTest {

	/*
	 * Object of type AuthUser
	 */
	private AuthUser user;

	/*
	 * Initializing passwordEncoder and user objects before each test case
	 */
	List<String> list;

	@BeforeEach
	public void setUser() {
		user = new AuthUser();
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
		user.setCreated( LocalDateTime.now());
		user.setCreatedBy("me");
		user.setModified(LocalDateTime.now());
		user.setEnabled(true);
	    list=Arrays.asList("sup1", "sup2", "sup3");
		user.setLastThreePasswords(list);
		
	}

	/**
	 * Testing getPassword() function
	 */
	@Test
	void testGetPassword() {
		String expected = "Sonal@123";
		String actual = user.getPassword();
		assertEquals(expected, actual);
	}

	/*
	 * Testing getPassword() function
	 */
	@Test
	void testGetEmail() {
		String expected = "sonal@gmail.com";
		String actual = user.getEmail();
		assertEquals(expected, actual);
	}

	/**
	 * Testing getFirstName() function
	 */

	@Test
	void testFirstName() {
		String expected = "Sonal";
		String actual = user.getFirstName();
		assertEquals(expected, actual);
	}
	@Test
	void testLastName() {
		String expected = "Kumar";
		String actual = user.getLastName();
		assertEquals(expected, actual);
	}
	/*
	 * Testing AccountNonExpired function
	 */
	@Test
	void testAccountNonExpired() {
		assertTrue(user.isAccountNonExpired());
	}

	/*
	 * Testing isAccountNonLocked function
	 */
	@Test
	void testAccountNonLocked() {
		assertTrue(user.isAccountNonLocked());
	}

	/*
	 * Testing isCredentialsNonExpired function
	 */
	@Test
	void testCredentialsNonExpired() {
		assertTrue(user.isCredentialsNonExpired());
	}

	/*
	 * Testing isEnabled function
	 */
	@Test
	void testIsEnabled() {
		assertTrue(user.getEnabled());
	}
	@Test
	void testGetId() {
		Long expected = 1L;
		Long actual = user.getId();
		assertEquals(expected, actual);
	}
	@Test
	void GetModifified()
	{
		LocalDateTime actual = user.getModified();
		assertNotNull(actual);
	}
	@Test
	void GetCreated()
	{
		LocalDateTime actual = user.getCreated();
		assertNotNull(actual);
	}
	@Test
	void GetCreatedBy() {
		String expected = "me";
		String actual = user.getCreatedBy();
		assertEquals(expected, actual);
	}
	@Test
	void GetModififiedBy() {
		String expected = "me";
		String actual = user.getModifiedBy();
		assertEquals(expected, actual);
	}
	
	@Test
	void GetLastThreePasswords() {
		List<String> expected = list;
		List<String> actual = user.getLastThreePasswords();
		assertEquals(expected, actual);
		
	}
	
}
