Feature: New Order Validation

  Scenario: Validate New Order in WebOrder Page
    Given the demoUser enters "Tester" username
    Then the demoUser enters "test" password
    Then the user enter product info "MyMoney" and "10"
    And the user enters address info "David", "2200 E Devon Ave", "Des Plaines", "Illinois", "60018"
    * the user enters payment info "Visa", "1111222233334444", "06/24"
    Then the user validate success message

