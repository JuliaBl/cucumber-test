#Language ui
Feature: Login
  Background:
    Given user
      |userName|
      |student|

@initiate @logOut
Scenario: Login to the Mantis with valid credentials
Given user has opened Mantis login page
Then user specify valid credentials and click on button Login
  |  userName  | password  |
  |  student   | luxoft    |

@initiate @driverQuit
Scenario: Login to the Mantis with invalid credentials
Given user has opened Mantis login page
Then user specify invalid credentials and click on button Login
      |  userName  | password  |
      |  student   | new       |


