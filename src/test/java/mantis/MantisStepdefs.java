package mantis;

import cucumber.api.java.After;
import mantis.domain.User;
import mantis.pageobjects.LoginPage;
import mantis.pageobjects.ManagePage;
import mantis.pageobjects.ManageProjectPage;
import mantis.pageobjects.ViewPage;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static mantis.setup.SeleniumDriver.getDriver;

public class MantisStepdefs {
    private String projectName;
    private LoginPage loginPage;
    private ViewPage viewPage;
    private ManagePage managePage;
    private ManageProjectPage manageProjectPage;

    @Before("@initiate")
    public void setUp(){
        loginPage = new LoginPage();
        projectName = "ProjectName_" + new Random().ints(0, 1000).boxed().findFirst().get();
    }

    @After("@logOut")
    public void logOut(){
              viewPage
              .clickOnLogOut();
    }

    @After("@driverQuit")
    public void tearDown(){
        getDriver().quit();
    }

    @Given("^user$")
    public void user(List<User> users) {
       for(User u : users){
           System.out.println("User with login name " + u.getUserName() + " is initiated.");
       }
    }


    @Given("^user has opened Mantis login page$")
    public void user_has_opened_Mantis_login_page() {
     loginPage = loginPage
              .openPage(LoginPage.class);
    }

    @Then("^user specify valid credentials and click on button Login$")
    public void user_specify_valid_credentials_and_click_on_button_Login(List<User> userCredentials) {
      for(User u : userCredentials) {
       viewPage =
          loginPage
                  .tryLoginWithCredentials(u.getUserName(), u.getPassword(), ViewPage.class)
                  .openPage(ViewPage.class);
       assertEquals(u.getUserName(), viewPage.getLoggedInAsText());
      }
    }

    @Then("^user specify invalid credentials and click on button Login$")
    public void user_specify_invalid_credentials_and_click_on_button_Login(List<User> userInvalidCredentials) {
        for(User u : userInvalidCredentials) {
            loginPage
                    .tryLoginWithCredentials(u.getUserName(), u.getPassword(), LoginPage.class);
            assertEquals(loginPage.getErrorMessageForInvalidCredentials(), "Your account may be disabled or blocked or the username/password you entered is incorrect.");
        }
    }

    @Then("^user click on Manage link$")
    public void user_click_on_Manage_link() {

    }

    @Then("^user click Manage Projects$")
    public void user_click_Manage_Projects() {
     managePage =
         viewPage
                .clickOnManageLink();
    }

    @Then("^user click on button Create new Project$")
    public void user_click_on_button_Create_new_Project() {
     manageProjectPage =
        managePage
                .clickOnManageProjectLink();
    }

    @Then("^user specify project name and click on button Add Project$")
    public void user_specify_project_name_and_click_on_button_Add_Project() {
       manageProjectPage
               .clickOnCreateNewProject()
               .inputProjectName(projectName)
               .clickOnAddProject();
       assertEquals("There is no such project", manageProjectPage.isProjectAdded(projectName), true);
    }
}
