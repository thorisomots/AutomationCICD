@tag
Feature: Error vvalidation


@ErrorValidations
Scenario Outline:
Given I landed on the ecommerce page
When I logged in with username <name> and password <password>
Then "Incorrect email or password." message is displayed

Examples:
  | name   			   |	password	|
  | jakop.musk@x.com   |	@One12345	|
