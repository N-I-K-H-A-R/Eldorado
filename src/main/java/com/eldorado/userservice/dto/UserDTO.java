package com.eldorado.userservice.dto;

import org.springframework.context.annotation.Configuration;

import com.eldorado.userservice.entities.AuthUser;

/*
 * User DTO to receive Username and Password from RequestBody
 */
@Configuration
public class UserDTO {

	private String email;
	private String password;

	/**
	 * @return AuthUser
	 */
	public AuthUser toEntity() {
		AuthUser user = new AuthUser();
		user.setEmail(email);
		user.setPassword(password);
		return user;
	}

	/**
	 * @return the username
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param username the username to set
	 */
	public void setEmail(String username) {
		this.email = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}