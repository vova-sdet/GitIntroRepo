@api
Feature: Pet store

  Scenario: create a pet

    When user crates a pet with id, name, status
    Then the status code is OK
    And pet with id, name , status is created