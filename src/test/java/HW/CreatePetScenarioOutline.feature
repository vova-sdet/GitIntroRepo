Feature: Pet store

  Scenario Outline: create a pet

    When user crates a pet with "<id>", "<name>", "<status>"
    Then the status code is OK
    And pet with "<id>", "<name>", "<status>" is created

    Examples:
      | id   | name    | status   |
      | 1801 | Charlie | good boy |
      | 1298 | Baloo   | bad boy  |
      | 1310 | Jessie  | sold     |

