Feature: Validation of New order using Scenario Outline

  Scenario Outline: New Order Validation
    Given the demoUser enters "Tester" username
    Then the demoUser enters "test" password
    Then the user enter product info "<productName>" and "<quantity>"
    And the user enters address info "<name>", "<address>", "<city>", "<state>", "<zipcode>"
    * the user enters payment info "<cardType>", "<cardNum>", "<expirationDate>"
    Then the user validate success message
    And the user validate new order details "<productName>", "<quantity>", "<name>", "<address>", "<city>", "<state>", "<zipcode>", "<cardType>", "<cardNum>", "<expirationDate>"

    Examples:
      | productName | quantity | name    | address          | city        | state      | zipcode | cardType         | cardNum          | expirationDate |
      | MyMoney     | 10       | David   | 2200 E Devon Ave | Des Plaines | Illinois   | 60018   | Visa             | 1111222233334444 | 06/24          |
      | FamilyAlbum | 5        | John    | 2222 E Devon Ave | Chicago     | Arizona    | 50463   | MasterCard       | 1111222244443333 | 06/26          |
      | ScreenSaver | 3        | Jessica | 3454 E River Ave | Los Angeles | California | 50463   | American Express | 5555222244443333 | 02/26          |
