Feature: Contact And Shop

  Scenario: Verify the error messages
    Given I am on the "Home" page
    And  I am on the "Contact" page
    When I submit the form
    Then I Verify error messages as follows
    |Field   |ErrorMessage                                                                          |
    |Forename|Forename is required                                                                  |
    |Email   |Email is required                                                                     |
    |Message |Message is required                                                                   |
    |Header  |We welcome your feedback - but we won't get it unless you complete the form correctly.|
    And I enter mandatory Fields as follows
      |Field    |Data                 |
      |Forename |Roha                 |
      |Email    |roharizwan7@gmail.com|
      |Message  |Hello                |
   Then I verify error messages are gone
   And I close the page

Scenario: Verify that contact is submitted successfully
   Given I am on the "Home" page
   And  I am on the "Contact" page
   And I enter mandatory Fields as follows
      |Field    |Data                 |
      |Forename |Roha                 |
      |Email    |roharizwan7@gmail.com|
      |Message  |Hello                |
   And I submit the form
   Then I Verify the successful message as "Thanks Roha, we appreciate your feedback."
   And I close the page

Scenario: Verify the total
   Given I am on the "Home" page
   And  I am on the "Shop" page
   And I buy following items
   |Item           |Count |
   |Stuffed Frog   |2     |
   |Fluffy Bear    |5     |
   |Valentine Bear |3     |
   And I am on the "Cart" page
   Then I Verify the shopping
   |Item          |Price |Quantity|SubTotal|
   |Stuffed Frog  |$10.99|2       |$21.98|
   |Fluffy Bunny  |$9.99 |5       |$49.95|
   |Valentine Bear|$14.99|3       |$44.97|
   And I verify the total "Total: 116.9"
   And I close the page


