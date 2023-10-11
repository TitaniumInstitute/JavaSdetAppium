  Feature: Customer can choose to pay on collection

  Rule: Customers can choose for customer-collection orders to be pay-on-collection

  # Declarative
  Scenario: Customer choose yo pay on collection
    Given the customer has chosen to collect their order
    When they choose to pay on collection
    Then they should be provided with an order confirmation

   # Imperativa
#   Scenario: Customer choose yo pay on collection
#     Given I navigate to WIF app url "www.wimf.com"
#     When I select from dropdown "collect-order"
#     And I click "pay on collection" button
#     And I type email "test@gmail.com"
#     And I type phone "2323232323"
#     And I type name "Test"
#     And I type lastname "Test LastName"
#     Then I shouls see and pop up window
#     And The information must be displayed