
@tag
Feature: Validate error handling for the ecommerse page

  Background: 
  
  Given I landed on the ecommerce page

  @ErrorValidation
  Scenario: 
    Given I logged in with username <username> and password <password>
    Then Expected error message <expectedError> message is displayed on confirmationPage
    
    Examples: 
      | username | password | expectedError   |
      | mayureshpavnoji@gmail.com | Placed@1007  | Incorrect email or password. |

