Feature: Verify CRUD Operations, People Call-3, Star Ships-9, Species-3 of the Star Wars API

  Scenario: Verify user can only fetch information
    Given StarWars API
    When User makes "GET" call to "People3"
    Then 200 status code should be returned
    When User makes "DELETE" call to "People3"
    Then 405 status code should be returned
    When User makes "POST" call to "People3"
    Then 405 status code should be returned
    When User makes "PUT" call to "People3"
    Then 405 status code should be returned


  Scenario: Verify people/3
    Given StarWars API
    When User makes "GET" call to "People3"
    Then 200 status code should be returned
    And  "name" in response should be equal to "R2-D2"
    And  "skin_color" in response should be equal to "white, blue"
    And  "films" in response should be equal to "A New Hope,The Empire Strikes Back,Return of the Jedi,The Phantom Menace,Attack of the Clones,Revenge of the Sith"


  Scenario: Verify starships/9/
    Given StarWars API
    When User makes "GET" call to "Starships9"
    Then 200 status code should be returned
    And  "name" in response should be equal to "Death Star"
    And  "crew" in response should be equal to "342,953"


  Scenario: Verify species/3
    Given StarWars API
    When User makes "GET" call to "Species3"
    Then 200 status code should be returned
    And  "name" in response should be equal to "Wookie"
    And  "classification" in response should be equal to "mammal"
    And  "homeworld" in response should be equal to "Kashyyyk"


  Scenario: Verify wookiee format
    Given StarWars API
    And queryparam "format" is "wookiee"
    When User makes "GET" call to "Planets14"
    Then 200 status code should be returned
    And response in "wookiee" format should match "planets.json"

