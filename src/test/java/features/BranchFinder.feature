Feature: Branch Finder Feature

  Scenario Outline: Print and log the phone no. of nearest branch
    Given user is on Lloyds Banking Group's "home" page
    When  user clicks on "branch finder"
    And   user enters "<Area>" postcode and click search
    Then  all nearest branches should be displayed
    When user clicks on "View Branch" of "first" branch
    Then user is taken to "first" branch's page
    Examples:
      | Area          |
      | EastLondon    |
      | WestLondon    |
      | NorthLondon   |
      | SouthLondon   |
      | CentralLondon |
      | Manchester    |
      | Reading       |
      | Swindon       |
      | Bristol       |
      | Bath          |
