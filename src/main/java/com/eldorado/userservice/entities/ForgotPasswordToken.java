package com.eldorado.userservice.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Configuration;

/**
 * Entity Class for forgot_password_token send to user to his email id
 */
@Entity
@Table(name = "forgot_password_token")
@Configuration
public class ForgotPasswordToken {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tokenid")
	private long tokenid;

	@Column
	private String forgotPassToken;

	@Column
	private String createdDateTime;

	/**
	 * One to One mapping between user and ForgotPasswordSteps token entities
	 */
	@OneToOne(targetEntity = AuthUser.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "id")
	private AuthUser user;

	public ForgotPasswordToken() {
	}

	public ForgotPasswordToken(AuthUser user) {
		this.user = user;
		createdDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
		forgotPassToken = UUID.randomUUID().toString();
	}

	/**
	 * @return the tokenid
	 */
	public long getTokenid() {
		return tokenid;
	}

	/**
	 * @param tokenid the tokenid to set
	 */
	public void setTokenid(long tokenid) {
		this.tokenid = tokenid;
	}

	/**
	 * @return the forgotPassToken
	 */
	public String getForgotPassToken() {
		return forgotPassToken;
	}

	/**
	 * @param forgotPassToken the forgotPassToken to set
	 */
	public void setForgotPassToken(String forgotPasswordToken) {
		this.forgotPassToken = forgotPasswordToken;
	}

	/**
	 * @return the createdDateTime
	 */
	public LocalDateTime getCreatedDateTime() {
		return LocalDateTime.parse(createdDateTime, DateTimeFormatter.ISO_DATE_TIME);
	}

	/**
	 * @param createdDateTime the createdDateTime to set with LocalDateTime to
	 *                        String conversion
	 */
	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
	}

	/**
	 * @param createdDateTime the createdDateTime to set
	 */
	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	/**
	 * @return the userEntity
	 */
	public AuthUser getUser() {
		return user;
	}

	/**
	 * @param userEntity the userEntity to set
	 */
	public void setUser(AuthUser user) {
		this.user = user;
	}

}
