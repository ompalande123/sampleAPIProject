@API @Suite
Feature: User petstore API

  @create_user
  Scenario: Verify user is able to create new user
    Given user creates a post request with name "Ron" job "Accountant" and id "1241"
    When user receives the successful response
    Then user generating report

  @get_user
  Scenario: Verify user is able to get user data
    Given user creates get request with path parameter value as id 2
    When user receives the successful response
    Then user validates the response
    And user generating report

  @get_all_users
  Scenario: Verify user is able to get all users and perform validations on the repoonse
    Given user sends get request to retrieve all users from page number 2
    When user wants to validate the last name value
    Then user generating report

  @create_multiple_users
  Scenario Outline: Verify user is able to create multiple users
    Given user creates a post request with name "<name>" job "<job>" and id "<id>"
    When user receives the successful response
    Then user generating report

    Examples: 
      | name         | job      | id   |
      | Harry Potter | Magician | 1011 |
      | Steve Rogers | Captain  | 1012 |
