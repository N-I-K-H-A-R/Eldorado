package com.eldorado.userservice.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * User Entity Class
 */
@EntityListeners(AuditingEntityListener.class)
@Entity
@Configuration
public class AuthUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	private String firstName;
	private String lastName;

	@Column(name = "email", unique = true)
	@NotBlank(message = "Email is mandatory")
	private String email;

	@Column(name = "password")
	@NotBlank(message = "Password is mandatory")
	private String password;

	@ElementCollection
	private List<String> lastThreePasswords = new ArrayList<>();

	@Column(name = "enabled")
	private boolean enabled = false;
	@Column(name = "accountNonExpired")
	private boolean accountNonExpired = true;
	@Column(name = "credentialsNonExpired")
	private boolean credentialsNonExpired = true;
	@Column(name = "accountNonLocked")
	private boolean accountNonLocked = true;
	@CreatedBy
	@Column(nullable = false, updatable = false)
	private String createdBy;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime created;

	@LastModifiedBy
	@Column(nullable = false)
	private String modifiedBy;

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime modified;
	/*
	 * Many to Many mapping between user and role entities
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

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
	 * @return the accountNonExpired
	 */
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * @param accountNonExpired the accountNonExpired to set
	 */
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * @return the credentialsNonExpired
	 */
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @param credentialsNonExpired the credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * @return the accountNonLocked
	 */
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * @param accountNonLocked the accountNonLocked to set
	 */
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the id
	 *
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param the id
	 */
	public void setId(Long id) {
		this.id = id;
	}

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

	/**
	 * @return the lastThreePasswords
	 *
	 */
	public List<String> getLastThreePasswords() {
		return lastThreePasswords;
	}

	/**
	 * @param lastThreePasswords the lastThreePasswords to set
	 *
	 */
	public void setLastThreePasswords(List<String> lastThreePasswords) {
		this.lastThreePasswords = lastThreePasswords;
	}

	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(Boolean isVerified) {
		this.enabled = isVerified;
	}

	/*
	 * Return Type:String Value :user first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/*
	 * Function to set the user first name Return Type:void
	 *
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/*
	 * Return Type:String Value :user last name
	 */
	public String getLastName() {
		return lastName;
	}

	/*
	 * Return Type:Long Value :user id
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*
	 * Function to get user roles Return Type:Set Value :user roles set
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/*
	 * Function to set user roles Return Type:void
	 *
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the created
	 */
	public LocalDateTime getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the modified
	 */
	public LocalDateTime getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

}
