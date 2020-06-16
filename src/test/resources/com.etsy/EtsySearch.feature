Feature: Validate Search in Etsy

  Background: It will run before each scenario
    Given the user goes to Etsy


  @etsy @conditional
  Scenario: Validate of search in Etsy 1
    When the user search with "winter hats" in Etsy
    Then the user validate title "Winter hats | Etsy" and "winter%20hats" url

  @etsy
  Scenario: Validate of search in Etsy 2
    When the user search with "hat" in Etsy
    Then the user validate title "Hat | Etsy" and "hat" url

  @etsy @conditional
  Scenario: Validate of search in Etsy 3
    When the user search with "winter soldier" in Etsy
    Then the user validate title "Winter soldier | Etsy" and "winter%20soldier" url

