package com.eldorado.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eldorado.userservice.entities.Role;

/*
 * Jpa repository for User Roles
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String name);

}