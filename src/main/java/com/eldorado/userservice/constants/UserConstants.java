package com.eldorado.userservice.constants;

/**
 * Constant Class for Storing all Hard Coded Values
 */
public final class UserConstants {
	private UserConstants() {

	}


	// All Hard Coded Values
	public static final String PASS_DESCRIPTION = "password should only contain atleast one lowercase character, one uppercase character, one digit, one special character, and length between 8 to 20.";
	public static final String PASS_CANNOT_BE_EMPTY = "password cannot be empty";
	public static final String PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
	public static final String USERNAME_CANNOT_BE_EMPTY = "username cannot be empty";
	public static final String USERNAME_DESCRIPTION = "username should only contain alphabets, numbers or special characters .,_,- ";
	public static final String USER_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
	public static final String VALID_TOKEN = "Valid Token!!";
	public static final String INVALID_TOKEN = "Invalid Token!!";
	public static final String TOKEN_CANNOT_BE_EMPTY = "Token Cannot Be Empty!!";
	public static final String USER_REGISTERED_SUCCESSFULLY = "Email Verified and User Registration Successfull!!";
	public static final String EMAIL_SENT_SUCCESSFULLY = "Email send successfully Please Verify!!";
	public static final String NOT_VERIFIED = "User not verified.";
	public static final String TOKEN_ALREADY_USED = "Token Already Used And User is Verified";
	public static final String USER_VERIFIED = "User Verified";
	public static final String NO_USER_FOUND = "No User Found ";
	public static final String USER_ALREADY_EXIST = "User Already Exist";
	public static final String USER_ALREADY_EXIST_EXCEPTION = "There is an account with that email address: ";
	public static final String USER_SUCCESSFULLY_REGISTER = "User Successfully Registered";
	public static final String REGISTRATION_STARTED = "User Registeration Started";
	public static final String FAILED_TO_RUN = "Something went wrong. Please try again later!";
	public static final String EMAIL_VERIFICATION_BODY = "To confirm your account, please click here : ";
	public static final String CONFIRM_ACCOUNT_ENDPOINT="confirm-account/";
	public static final String PASS_RECOVERY_STARTED = "User has forgotten the password.";
	public static final String PASS_RECOVERY_EMAIL_SENT = "We have sent a reset password link to your email. Please check.";
	public static final String NEW_PASS = "Enter New Password.";
	public static final String RESET_PASS_FORM = "Reser Password Form Displayed.";
	public static final String TOKEN_EXPIRED = "Password Reset Token has Expired.";
	public static final String PASS_CHANGE_INITIATED = "Password changing in progress.";
	public static final String TOKEN_NOT_USER = "Forgot Password Token found but User not found.";
	public static final String PASS_CHANGED = "Password Changed Successfully.";
	public static final String NULL_MAIL = "Null eldorado Mail.";
	public static final String ENVIRONMENT_EMAIL = "spring.mail.username";
	public static final String REGISTRATION_EMAIL_SUBJECT = "Complete Registration!";
	public static final String FORGOT_PASS_EMAIL_SUBJECT = "Reset Password";
	public static final String LAST_THREE_PASSES = "This password is same as last 3 passwords, please choose any other password.";
	public static final String VERIFICATION_EMAIL_SENT="We have already sent a verification link to your email. Please check!!";
	public static final String PLEASE_LOGIN=" Please Login!!";
	public static final String LOGIN_URL="http://localhost:3000/login";
	public static final String ROLE_USER="ROLE_USER";
	public static final String ROLE_NOT_FOUND="Error: Role is not found.";
	public static final String ELDORADO_AUDITOR="Eldorado Auditor";
	public static final String CONFIRM_FORGOT_PASS_FRONTEND = "confirm-reset-password/";
	public static final String INVALID_EMAIL_FORMAT = "Email format is wrong. Please enter valid email.";
	public static final String RESET_PASS_FORM_SERVICE = RESET_PASS_FORM + " - in Service";
	public static final String RESET_PASS_FORM_FILTER = RESET_PASS_FORM + " - in Filter";
	public static final String PASS_CHANGE_INITIATED_FILTER = PASS_CHANGE_INITIATED + " - in Filter";
	public static final String PASS_CHANGE_INITIATED_SERVICE = PASS_CHANGE_INITIATED + " - in Service";
	public static final String CHANGING_PASS = "Password change initiated.";
	public static final String PROCESS_FORGOT_PASS = "Processing Forgot Password.";
	public static final String PROCESS_FORGOT_PASS_FILTER = PROCESS_FORGOT_PASS + " in Filter";
	public static final String PROCESS_FORGOT_PASS_SERVICE = PROCESS_FORGOT_PASS + " in Service";
	public static final String INVALID_PASS_FORMAT = "Password format is wrong. Please enter valid password.";
	public static final String PASS_CHANGE_STARTED = "User wants to change the password.";
	public static final String INVALID_PASS = "Incorrect password";
}

