package com.eldorado.userservice.dto;

import org.springframework.context.annotation.Configuration;

/*
 * Change Password DTO to receive Email and Password from RequestBody
 */
@Configuration
public class ChangePasswordDTO {
	private String email;
	private String currentPassword;
    private String newPassword;
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the currentPassword
	 */
	public String getCurrentPassword() {
		return currentPassword;
	}
	/**
	 * @param currentPassword the currentPassword to set
	 */
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}
	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
