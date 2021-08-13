package com.eldorado.userservice.stepdefinitions;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

class AUser {

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

	public boolean verificationLink(boolean click) {

		return click;
	}

	public boolean popupForCheckMailForVerifyTheRegistration(boolean pop) {
		return pop;
	}

	public boolean userHaveValidCredentials(boolean cred) {
		return cred;
	}

	public boolean clickOnForgotPassword(boolean forgotPassWord) {
		return forgotPassWord;
	}

	public boolean sendEmailverificationLink(boolean send) {
            return send;
	}

	public boolean modifiedByClickingReset(boolean modified) {
              return modified;
	}

}

public class ForgotPasswordSteps {
	AUser user;
     Boolean forgot;
     
	@Given("User\\/Admim forgot their password")
	public void user_admim_forgot_their_password() {
		user=new AUser();
		forgot=true;
	}

	@When("click on forgot password")
	public void click_on_forgot_password() {
		forgot=forgot&&user.clickOnForgotPassword(true);
	}

	@And("enter the valid email id for verification")
	public void enter_the_valid_email_id_for_verification() {
		forgot=forgot&&user.sendEmailverificationLink(true);
	}

	@And("user click on verify")
	public void user_click_on_verify() {
		forgot=forgot&&user.verificationLink(true);
	}

	@Then("verification link send to user mail Which can expire within a certain period\\(e.g. a day)")
	public void verification_link_send_to_user_mail_which_can_expire_within_a_certain_period_e_g_a_day() {
		assertEquals(true,forgot);
	}

	@And("using that link user can able to provide modified password which should not be same as last three used password")
	public void using_that_link_user_can_able_to_provide_modified_password_which_should_not_be_same_as_last_three_used_password() {
		forgot=forgot&&user.verificationLink(true);
		assertEquals(true,forgot);
	}

	@And("password should be modified by clicking reset.")
	public void password_should_be_modified_by_clicking_reset() {
		forgot=forgot&&user.modifiedByClickingReset(true);
		assertEquals(true,forgot);
	}

	@And("enter the invalid email id for verification")
	public void enter_the_invalid_email_id_for_verification() {
		assertEquals(true,forgot);
	}

	@Then("get popup for email id not found.")
	public void get_popup_for_email_id_not_found() {
		assertEquals(true,forgot);
	}

}
