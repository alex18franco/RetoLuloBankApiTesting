Feature: Interaction with api Employees

  Background: Call the Api
    Given that Alex wants to call the api

  Scenario: get employees
    When he get all employees
    Then he see the status message is success
    And he see the status code 200
    And he see the message Successfully! All records has been fetched.

  Scenario Outline: post employee
    When he create a new employee
      |employee_name|employee_salary|employee_age |
      |<name>       |<salary>       |<age>        |
    And he see the status code 200
    Then he see the attribute id is not empty
    And he see the message Successfully! Record has been added.

    Examples:
      |name|salary |age|
      |Alex  |1200000|20 |
      |Andrea|342500 |24 |

  Scenario Outline: get employee by id
    When he search the employee with <id>
    Then he see the status message is success
    And he see the employee with data
      |id   |employee_name|employee_salary|employee_age |profile_image  |
      |<id> |<name>       |<salary>       |<age>        |<profile_image>|
    And he see the status code 200
    And he see the message Successfully! Record has been fetched.

    Examples:
      |id |name       |salary|age|profile_image|
      |12 |Quinn Flynn |342000|22 |             |
      |24 |Doris Wilder|85600 |23 |             |

  Scenario Outline: delete employee by id
    When he delete the employee with <id>
    Then he see the status message is success
    And he see the status code 200
    And he see the message Successfully! Record has been deleted

    Examples:
      |id|
      |12 |
      |24 |