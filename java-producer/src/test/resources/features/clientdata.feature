Feature: Client Service

  Producer responds with Client data

  Scenario: Calling client data yields client objects
    Given the Client Service is running
    When John calls the client service for client ref 1
    Then it tells John that the client is called MyName
    And it tells John that the location is London