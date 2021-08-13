package com.eldorado.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eldorado.userservice.entities.AuthUser;

/**
 * UserRepository for jpa
 * 
 */
@Repository
public interface UserRepository extends JpaRepository<AuthUser, Long> {
	AuthUser findByEmailIgnoreCase(String username);
}
