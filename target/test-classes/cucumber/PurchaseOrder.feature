
@tag
Feature: Purchase the order from ecommerse website

  Background: 
  
  Given I landed on the ecommerce page

  @Regression
  Scenario: 
    Given I logged in with username <username> and password <password>
    When I add product <productName> to the cart
    And CheckOut <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage


    Examples: 
      | username | password | productName   |
      | mayureshpavnoji1@gmail.com | Placed@1007  | IPHONE 13 PRO |

