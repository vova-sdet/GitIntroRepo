Feature: OrangeHRM Login Page Test

  Scenario: Login With Valid Credentials Test

    Given the user enters "Admin" username
    Then the the user enters "admin123" password
    And the user click login button
    * the user validates title of the page is "OrangeHRM"

  Scenario: Checkbox Click And Username In Descending Order Validation

    Given the user click admin function
    Then the user click first checkbox next to the username
    And validate all checkboxes are selected
    Then the user double click on top of the username tab
    And validate all usernames are listed in descending order

  Scenario: User Edition Message Validation

    Given the user click admin function
    And the user click add button
    Then the user select "ESS" user's role
    And the user select "Linda Anderson" as the employee name
    And the user enter "QueenLinda" as the username
    And the user select "Enabled" status
    And the user enter "LindaBoss777" password and confirm
    Then the user click Save button
    * Validate "Successfully Saved" message

  Scenario: User Update Info Validation

    Given the user click admin function
    And the user search with the "QueenLinda" username
    And validate only one user displayed after search
    And validate Username is "QueenLinda"
    And validate User Role is "ESS"
    And validate Employee Name is "Linda Anderson"
    And validate status of the employee is "Enabled"
    Then click on top the username
    And click edit button
    And change the Status to "Disabled"
    * validate "Successfully Updated" message
    Then Search with the "QueenLinda" username
    * validate status for the user is updated to "Disabled"

  Scenario: User Deletion Validation

    Given the user click admin function
    And the user search with the "QueenLinda" username
    Then the user click the checkbox for the found user
    Then the user click delete button
    And Validate popup is displayed
    Then the user click OK button
    And validate "Successfully Deleted" message
    Then the user search with the "QueenLinda" username
    And validate "No Records Found" text