package com.eldorado.userservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eldorado.userservice.entities.ConfirmationToken;

/**
 * confirmationTokenRepository for jpa
 * 
 */
@Repository("confirmationTokenRepository")
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {

	public ConfirmationToken findByConfirmedToken(String confirmedToken);
}