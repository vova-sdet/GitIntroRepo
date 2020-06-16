Feature: WebOrder Login Page Test

#  Scenario: Login Page Positive Test
#    Given the demoUser enters username
#    When the demoUser enters password
#    Then the demoUser clicks the login button
#    And the demoUser validate the home page

  @smoke
  Scenario: Login Page Positive Test
    Given the demoUser enters "Tester" username
    When the demoUser enters "test" password
    And the demoUser validate the home page

  @negative @smoke @conditional
  Scenario: Login Functionality Negative Test
    Given the demoUser enters "techtorial" username
    When the demoUser enters "test" password
    Then the demoUser validate "Invalid Login or Password." text

  @negative @smoke
  Scenario: Login Functionality Negative Test 2
    Given the demoUser enters "Tester" username
    When the demoUser enters "wrong" password
    Then the demoUser validate "Invalid Login or Password." text

  @negative @smoke
  Scenario: Login Functionality Negative Test
    Given the demoUser enters "techtorial" username
    When the demoUser enters "badPassword" password
    Then the demoUser validate "Invalid Login or Password" text
