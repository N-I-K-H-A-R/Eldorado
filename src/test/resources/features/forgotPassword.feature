Feature: Test forgot password functionality

  @validemail
  Scenario: check forgot password function is successfull with valid mail id
    Given User/Admim forgot their password
    When click on forgot password
    And enter the valid email id for verification
    And user click on verify
    Then verification link send to user mail Which can expire within a certain period(e.g. a day)
    And using that link user can able to provide modified password which should not be same as last three used password
    And password should be modified by clicking reset.

  @Invalidemail
  Scenario: check forgot password function is failure with invalid mail id
    Given User/Admim forgot their password
    When click on forgot password
    And enter the invalid email id for verification
    And user click on verify
    Then get popup for email id not found.
