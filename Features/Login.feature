Feature: Verify that user is able to login into application 

Scenario: Verify Login functionality

Given Verify Login Page Title with "Free CRM"
When User Login into Application
Then Home Page Open
Then Logout from Application