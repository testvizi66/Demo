@tag
Feature: Purchase the order from Ecommerce Website

Background:
Given I landed on Ecommerce page

@Order
Scenario Outline:Positive test of submitting the order
Given Logged in with username <name> and password <password>
When Add product <productName>in cart
And Checkout <productName> and submit the order.
Then "Thankyou for the order." is displayed on confirmation page.
Examples:
|name                        |password                  |    productName           |
|rameshtalekar15@gmail.com   |Ramesh@15                 |ZARA COAT 3               |