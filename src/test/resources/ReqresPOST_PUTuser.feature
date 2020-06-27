Feature: Update Information

 # @reqres @create
  Scenario: Create new user
    When "create" user at reqres.in
    Then Verify status code 201
    And  Verify "created" information

 # @reqres @update
  Scenario: Update user
    When "update" user at reqres.in
    Then Verify status code 200
    And  Verify "updated" information