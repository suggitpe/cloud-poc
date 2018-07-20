Feature: Greeting Service

  Producer responds with greeting

  Scenario: Calling Producer yields greeting
    Given the Producer is running
    When Bob calls the Producer
    Then it says "Hello, Bob"

  Scenario: Calling the Producer anonymously yields greeting to whole world
    Given the Producer is running
    When someone called the Producer anonymously
    Then it says "Hello, World"