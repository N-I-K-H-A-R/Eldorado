package com.eldorado.userservice.entities;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.context.annotation.Configuration;

/**
 * Entity Class for confirmation_token send to user to his email id
 */
@Entity
@Table(name = "confirmation_token")
@Configuration
public class ConfirmationToken {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tokenid")
	private long tokenid;

	@Column
	private String confirmedToken;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	/**
	 * One to One mapping between user and confirmation token entities
	 */
	@OneToOne(targetEntity = AuthUser.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "id")
	private AuthUser user;

	public ConfirmationToken() {
	}

	public ConfirmationToken(AuthUser user) {
		this.user = user;
		createdDate = new Date();
		confirmedToken = UUID.randomUUID().toString();
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
	 * @return the confirmationToken
	 */
	public String getConfirmationToken() {
		return confirmedToken;
	}

	/**
	 * @param confirmationToken the confirmationToken to set
	 */
	public void setConfirmationToken(String confirmationToken) {
		this.confirmedToken = confirmationToken;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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