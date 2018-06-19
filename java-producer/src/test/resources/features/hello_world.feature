Feature: Hello World

  Producer responds with hello world

  Scenario: Calling Producer yields hello world
    Given the Producer is running
    When I call the Producer
    Then it says hello