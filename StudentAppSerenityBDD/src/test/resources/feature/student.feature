Feature: Testing different requests on the student application

  @SMOKE
  Scenario: Check if student application can be accessed by users
    When User sends a GET request to the list endpoint, they must get back a valid status code 200
	
  Scenario Outline: Create a new student & verfiy if the student is added
    When User creates a new student by providing the firstName <firstName> lastName <lastName> email <email> programme <programme> courses <courses>
    Then User verifies the student with <email> is created

    Examples: 
      | firstName  | lastName | email           | programme        | courses |
      | Kristopher | Todd     | test@gmail.com  | Computer Science | JAVA    |
      | Peggy      | Todd     | test2@gmail.com | Computer Science | Python  |
