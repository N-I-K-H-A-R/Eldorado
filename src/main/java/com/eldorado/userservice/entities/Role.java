package com.eldorado.userservice.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;

/*
 * Role Pojo class or user role entity
 * Implementing granted authority interface to provide Authority for a role 
 */

@Entity
@Configuration
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	/*
	 * Many to Many mapping between user and role entities
	 */
	@ManyToMany(mappedBy = "roles")
	private Set<AuthUser> users;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the authority
	 */
	@Override
	public String getAuthority() {
		return name;
	}

}
