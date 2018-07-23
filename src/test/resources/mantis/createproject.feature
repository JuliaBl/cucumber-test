#Language ui
Feature: Login
  Background:
    Given user
      |userName|
      |student|

  @initiate @logOut
  Scenario: Create new Project
    Given user has opened Mantis login page
    When user specify valid credentials and click on button Login
      |  userName  | password  |
      |  student   | luxoft    |
    And user click on Manage link
    And user click Manage Projects
    And user click on button Create new Project
    Then user specify project name and click on button Add Project

  @initiate @driverQuit
  Scenario: Create new Project by open url for new project
    Given user has opened Mantis login page
    When user specify valid credentials and click on button Login
      |  userName  | password  |
      |  student   | luxoft    |
    And user open new Project url
    And user click on button Create new Project
    Then user specify project name and click on button Add Project



