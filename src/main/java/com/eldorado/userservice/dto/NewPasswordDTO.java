package com.eldorado.userservice.dto;

import org.springframework.context.annotation.Configuration;

/*
 * User DTO to receive Password from RequestBody
 */
@Configuration
public class NewPasswordDTO {

	private String password;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
