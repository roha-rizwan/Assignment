Feature: Contact And Shop

  Scenario: Verify the error messages
    Given I am on the "Contact" page
    When I sumbit the form
    Then I Verify error messages as follows
    |Field|ErrorMessage|
    |Forename|Forename is required|
    |Message|Message is required|
    And I enter mandatory Fields as follows
      |Field    |Data|
      |Forename |Roha|
      |Email    |roharizwan7@gmail.com|
      |Message  |Hello|

    #And I sumbit the form
    Then I verify error message are gone
    And I close the page

  Scenario: Verify the total
    Given I am on the "Home" page
    And  I am on the "Shop" page
    And I buy following items
    |Item|Count|
    |Stuffed Frog|2|
    | Fluffy|5     |
   And I am on the "Cart" page
    Then I Verify the shopping
    |Item|Price|Quantity|SubTotal|
    |Stuffed Frog|$10.99|2|$21.98|
    |Fluffy Bunny|$9.99| 5|49.95|



