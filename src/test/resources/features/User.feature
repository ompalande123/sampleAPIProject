@Users @Suite
Feature: User petstore API

	@testDataTableScenario
  Scenario: Verify user is able to read step data table
    Given user wants to read data
      | name       | age | gender | occupation | salary |
      | Ethan Hunt |  28 | Male   | MI Agent   |  80000 |


	@parseComplexJSON
	Scenario: Parse complex JSON response dummy
	Given user wants to parse json data
	
	
	
	
  @read_user_from_testData
  Scenario: Verify user is able to read data from excel test data sheet
    Given user wants to read test data from sheet "Create_Users" with ID "2"
    #When user receives the successful response
    Then user generating report

  @readAll_users_from_testData
  Scenario Outline: Verify user is able to read all data from excel test data sheet
    Given user wants to read test data from sheet "Create_Users" with ID "2"
    When user read "<Username>" and "<Jobname>" from sheet
    Then user generating report

    Examples: 
      | Username | Jobname |
      | Username | Jobname |

  @create_user_from_testData
  Scenario: Verify user is able to post a request for user creation
    Given user wants to read test data from sheet "Create_Users" with ID "3"
    When user creates a post request for new user creation
    Then user receives the successful response
    And user generating report

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

  @update_user
  Scenario: Verify user is able to update the existing user
    Given user creates a patch request with name "Tester" job "Automation"
    When user receives the successful response
    Then user generating report

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
