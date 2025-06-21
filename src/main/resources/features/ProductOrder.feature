Feature: Product Order Functionality

  As a logged-in user
  I want to add a product to the cart and complete the order
  So that I can purchase items from SauceDemo

  Scenario: Successfully order a product
    Given User insert username and password and click login button
    Then  Product page is displayed and user clicking on add to cart
    When  Product add to cart and displayed in cart page
    And   User proceed to checkout and insert personal details after that will complete the order
    Then  User should see the order confirmation
    And   User should see the success message