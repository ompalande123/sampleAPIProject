#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@API
Feature: User petstore API
  
  @create_user
  Scenario: Read data from excel to create user and validate the status
    #Given user wants to create an user by reading test id "1" in sheet "Create_Users"
    #And user creates a post request
    And user creates a post request with "Ron" "Accountant" and "1241"
    #And user wants to get data for username "tester"
    And user wants to get data for username 2
    #When user creates a valid post request and send the request
    #Then user validates the response
    #And user generating report
    
    @get_all_users_and_validate
    Scenario: get all users and perform validation
    Given user sends get request to retrieve all users from page number 2
    Then user validate the response


	 @tag2
  Scenario Outline: Create multiple users
    When user creates a post request with "<name>" "<job>" and "<id>" 
    #When I check for the <value> in step
    #Then I verify the <status> in step

    Examples: 
      | name | job | id |
      | Harry Potter | Magician | 1011 |
      | Steve Rogers | Captain | 1012 |

