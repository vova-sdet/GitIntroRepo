Feature: Scenario Outline Test

  Background:
    Given the user goes to Etsy

#  Scenario Outline: and Scenario Template: are same
#  Examples and Scenarios are the same

  @etsyOutline
  Scenario Outline: Etsy Search Validation with Outline
    When the user search with "<searchValue>" in Etsy
    Then the user validate title "<title>" and "<etsyUrl>" url
    Examples:
      | searchValue    | title                  | etsyUrl          |
      | winter hats    | Winter hats \| Etsy    | winter%20hats    |
      | hat            | Hat \| Etsy            | hat              |
      | winter soldier | Winter soldier \| Etsy | winter%20soldier |