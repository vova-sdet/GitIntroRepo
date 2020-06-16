@smoke
Feature: Cucumber first test

  Scenario: First steps
    Given the user school name
    When the user print name
    Then the user print the last name
    And the user print the city
    * the user print the state

    # for cucumber we need to use Gherkin language
    # Feature file starts with Feature: keyword
    # for every test case we create the Scenario
    # We need to create the java code inside StepDefinitions