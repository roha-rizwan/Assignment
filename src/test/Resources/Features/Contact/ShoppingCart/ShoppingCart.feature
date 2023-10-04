Feature: Add items to the Shopping Cart and verify the total

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