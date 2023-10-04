Feature: Verify error messages on the Contact page and submit a new contact

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


