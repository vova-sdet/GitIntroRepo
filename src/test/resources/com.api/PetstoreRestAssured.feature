Feature: Getting and deserializing pet from petstore

  @petAPI
  Scenario Outline: get pet
    Given request type headers set to "application/json"
    When user executes "GET" request
    Then the status code is 200
    And content type header is "application/json"
    And user verifies <id>, "<name>", <tags> size

    Examples:
      | id  | name   | tags |
      | 113 | doggie | 1    |