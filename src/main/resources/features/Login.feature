@Login

Feature: User Login with valid username and password

  Scenario: Valid login
    Given User insert valid username and password
    And   User click on login button
    Then  User login successfully

  Scenario: Invalid login
    Given User insert invalid username and password
    And   User click on login button
    Then  User login failed