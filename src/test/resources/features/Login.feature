@Logins @Suite

Feature: Login functionality
  
  @successful_login
  Scenario: Verify user is able to login successful with valid credentials
    Given user sends a post request with email "eve.holt@reqres.in" and password "cityslicka"
    #When user receives the successful response
    #Then user captures the token value and status code
    And user generating report