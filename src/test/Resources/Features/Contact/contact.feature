Feature: Contact

  Scenario: Verify the error messages
    Given I am on the "Contact" page
    When I sumbit the form
    And I close the page
    Then I Verify error messages as follows
    |Field|ErrorMessage|
    |Forename|Forename is required|
    |Email |Email is required|
    ||Message is required|