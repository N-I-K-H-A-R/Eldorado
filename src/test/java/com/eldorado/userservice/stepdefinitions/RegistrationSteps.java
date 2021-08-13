package com.eldorado.userservice.stepdefinitions;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

class AppUser {

	public Boolean userWasNotRegistered(Boolean isRegister) {
		return isRegister;
	}

	public Boolean userCredential(Boolean details) {
		return details;
	}

	public Boolean clicksOnSignUp(Boolean signUp) {
		return signUp;
	}

	public Boolean registrationSuccessful(Boolean isRegister) {
		return isRegister;
	}

	public boolean userNotClickOnVerificationLink(boolean click) {

		return click;
	}

	public boolean popupForCheckMailForVerifyTheRegistration(boolean pop) {
		return pop;
	}

	public boolean userHaveValidCredentials(boolean cred) {
		return cred;
	}

}

public class RegistrationSteps {
	AppUser user;
	Boolean register;

	@Given("user is on registration page")
	public void user_is_on_registration_page() {
		user = new AppUser();
		register = true;
	}

	@And("user was not registered")
	public void user_was_not_registered() {
		register = register && user.userWasNotRegistered(true);
	}

	@When("user enters username and password")
	public void user_enters_username_and_password() {
		register = register && user.userCredential(true);

	}

	@And("clicks on sign up")
	public void clicks_on_sign_up() {
		register = register && user.clicksOnSignUp(true);
	}

	@Then("verfication email send to user")
	public void verfication_email_send_to_user() {
		assertEquals(true, register);
	}

	@And("registration successful after user clicks on verification link")
	public void registration_successful_after_user_clicks_on_verification_link() {
		register = register && user.registrationSuccessful(true);
		assertEquals(true, register);
	}

	@And("user not click the verification link")
	public void user_not_click_the_verification_link() {
		register = register && user.userNotClickOnVerificationLink(false);
		assertEquals(false, register);
	}

	@And("get popup for check mail for verify the registration")
	public void get_popup_for_check_mail_for_verify_the_registration() {
		register = !register && user.popupForCheckMailForVerifyTheRegistration(true);
		assertEquals(true, register);
	}

	@And("user have valid credentials")
	public void user_have_valid_credentials() {
		register = register && user.userHaveValidCredentials(true);
		assertEquals(true, register);
	}

	@Then("get popup for Account Already Created.")
	public void get_popup_for_account_already_created() {
		assertEquals(true, register);
	}

	@When("user clicks sign up without filling the required fields")
	public void user_clicks_sign_up_without_filling_the_required_fields() {
		register = register && user.userHaveValidCredentials(true);
	}

	@Then("get popup for fill the required fields.")
	public void get_popup_for_fill_the_required_fields() {
		assertEquals(true, register);
	}

}
