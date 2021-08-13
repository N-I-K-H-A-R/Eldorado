Feature: Test registration functionality

  @unregisteredUser
  Scenario: check sign up is successful for unregistered user
    Given user is on registration page
    And user was not registered
    When user enters username and password
    And clicks on sign up
    Then verfication email send to user
    And registration successful after user clicks on verification link
    
    
  @Bendingverification
  Scenario: check verification is successful for unregistered user
    Given user is on registration page
    And user was not registered
    When user enters username and password
    And clicks on sign up
    Then verfication email send to user
    And user not click the verification link
    And get popup for check mail for verify the registration 
  

  @registeredUser
  Scenario: check sign up is failure for registered user
    Given user is on registration page
    And user have valid credentials
    When clicks on sign up
    Then get popup for Account Already Created.

  @EmptyFieldScenario
  Scenario: Check sign up is failure for empty field entry
    Given user is on registration page
    When user clicks sign up without filling the required fields
    Then get popup for fill the required fields.
