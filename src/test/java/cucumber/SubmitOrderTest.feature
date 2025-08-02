
@tag
Feature: Purchase order from ecommerce website

Background:
Given I landed on the ecommerce page

  @Regression
  Scenario Outline: Positive Test of Submitting the order. 
  Given I logged in with username <name> and password <password>
  When I add product <productName> from Cart
  And checkout <productName> and submit the order
  Then "Thankyou for the order." message is displayed on the confirmation page
    
Examples:
  | name   			   |	password	|	productName	|
  | jakop.musk@x.com   |	@One1234	|	IPHONE 13 PRO	|
