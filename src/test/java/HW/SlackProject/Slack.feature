Feature: get, send and delete message via API and UI

  @one
  Scenario: one
    Given the user sends message to slack via POST request
    Then the user verifies the message via GET request

  @two
  Scenario: two
    Given the user sends message to slack via POST request
    Then the user verifies the message with Selenium WebDriver in UI

  @three
  Scenario: three
    Given the user sends message to slack with Selenium WebDriver in UI
    Then the user verifies the message with Selenium WebDriver in UI

  @four
  Scenario: four
    Given the user sends message to slack with Selenium WebDriver in UI
    Then the user verifies the message via GET request

  @five
  Scenario: five
    When the user deletes message from slack via POST request
    Then the user verifies the message is gone via GET request

  @six
  Scenario: six
    Given the user deletes message from slack via POST request
    Then the user verifies the message is gone via Selenium WebDriver in UI