package com.eldorado.userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eldorado.userservice.dto.ChangePasswordDTO;
import com.eldorado.userservice.dto.NewPasswordDTO;
import com.eldorado.userservice.dto.UserDTO;
import com.eldorado.userservice.entities.AuthUser;
import com.eldorado.userservice.filters.ChangePasswordFilter;
import com.eldorado.userservice.filters.ForgotPasswordFilterImpl;
import com.eldorado.userservice.filters.RegistrationFilter;

/**
 * Rest Controller Class for managing Endpoints
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/user")
public class UserController {


	@Autowired
	private ForgotPasswordFilterImpl forgotPasswordFilter;

	@Autowired
	RegistrationFilter registrationFilter;
	
	@Autowired
	private ChangePasswordFilter changePasswordFilter;

	/**
	 * Rest Controller Endpoint for Check
	 *
	 *
	 */

	@GetMapping("/health")
	public String demoCheck() {

		return "I AM Healthy";
	}

	/**
	 * Rest Controller Endpoint for User registration of user by his/her email and
	 * password
	 */
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserDTO user) {
		AuthUser authUser = user.toEntity();
		return registrationFilter.registerUserFilter(authUser);
	}

	/**
	 * Rest Controller Endpoint for verifying confirmation token send to user to
	 * his/her email id
	 *
	 */
	@GetMapping("/confirm-account/{token}")
	public ResponseEntity<String> confirmUserAccount(@PathVariable(value = "token") String confirmationToken) {
		return registrationFilter.verifyUserAccountFilter(confirmationToken);
	}

	/**
	 * Rest Controller Endpoint for finding if email of user is VERIFIED through
	 * username
	 *
	 */
	@GetMapping("/validUser/{username}")
	public ResponseEntity<String> confirmValidUser(@PathVariable(value = "username") String username) {
		return registrationFilter.confirmValidUserFilter(username);

	}

	/**
	 * Rest Controller Endpoint for receiving Password recovery Email on provided
	 * Email Id.
	 *
	 */
	@PostMapping("/forgot_password/{username}")
	public ResponseEntity<String> processForgotPassword(@PathVariable(value = "username") String email) {
		return forgotPasswordFilter.processForgotPasswordFilter(email);
	}

	/**
	 * Rest Controller Endpoint for verifying Password Recovery Link recieved on
	 * Email Id.
	 *
	 */
	@GetMapping("/reset_password/{token}")
	public ResponseEntity<String> showResetPasswordForm(@PathVariable(value = "token") String forgotPasswordToken) {
		return forgotPasswordFilter.showResetPasswordFormFilter(forgotPasswordToken);
	}

	/**
	 * Rest Controller Endpoint for changing password to new entered Password.
	 *
	 */
	@PostMapping("/reset_password/{token}")
	public ResponseEntity<String> processResetPassword(@RequestBody NewPasswordDTO newPassword,
			@PathVariable(value = "token") String forgotPasswordToken) {
		return forgotPasswordFilter.processResetPasswordFilter(forgotPasswordToken, newPassword.getPassword());
	}

	/**
	 * Rest Controller Endpoint for Changing Password of user by his/her email and
	 * current password
	 */
	@PostMapping("/update_password")
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO receivedDeatils) {
		return changePasswordFilter.processChangePasswordFilter(receivedDeatils.getEmail(),
				receivedDeatils.getCurrentPassword(), receivedDeatils.getNewPassword());
	}
	}

