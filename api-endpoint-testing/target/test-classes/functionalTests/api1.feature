Feature: API Validation

  Scenario: API Endpoint 1
    Given Api Endpoint 1 sends 200 status code
    When Api header
    When I validate size of response body of api endpoint
    And I validate content of response body

  Scenario: POST API Endpoint Request
    Given User validates post request