Feature: Validation of headers

  Scenario: Validation of new order headers
    Given the demoUser enters "Tester" username
    Then the demoUser enters "test" password
    When the user goes to the new order page
    And the user validate the product headers
      | Product:*       |
      | Quantity:*      |
      | Price per unit: |
      | Discount:       |
      | Total:          |

    # Task using dataTable validate the header for address information
  Scenario: Validation of new order address headers
    Given the demoUser enters "Tester" username
    Then the demoUser enters "test" password
    When the user goes to the new order page
    And the user validate the address headers

      | Customer name:* |
      | Street:*        |
      | City:*          |
      | State:          |
      | Zip:*           |