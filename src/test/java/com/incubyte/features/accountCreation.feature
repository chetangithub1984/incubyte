Feature: Generic reflection execution

  @TC01
  Scenario: Execute a class and method dynamically
    Given user executes method "clickOnCreateAnAccountLink" from page "HomePage"
    Given user executes method "createAnAccount" from page "CreateNewCustomerAccount"
    Given user executes method "signInButtonClick" from page "HomePage"
    Given user executes method "customerSignIn" from page "CustomerLoginPage"
    Given user executes method "myAccountWelcomePageCheck" from page "MyAccount"
