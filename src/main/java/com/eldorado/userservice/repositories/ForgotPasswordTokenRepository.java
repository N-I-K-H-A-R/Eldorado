package com.eldorado.userservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eldorado.userservice.entities.ForgotPasswordToken;

/**
 * forgetPasswordTokenRepository for jpa
 * 
 */
@Repository("forgotPasswordTokenRepository")
public interface ForgotPasswordTokenRepository extends CrudRepository<ForgotPasswordToken, String> {

	public ForgotPasswordToken findByForgotPassToken(String forgotPasswordToken);

}
