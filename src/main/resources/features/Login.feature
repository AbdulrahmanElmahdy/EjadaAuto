@Login

Feature: User Login with valid username and password

  Scenario: Valid login
    Given User insert username and password
    And   User click on login button
    Then  User login successfully