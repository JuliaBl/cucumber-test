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
import static mantis.setup.SeleniumDriver.close;

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
        close();
    }

    @Given("^user$")
    public void user(List<User> users) {
        users.stream().forEach(u -> System.out.println("User with login name " + u.getUserName() + " is initiated."));
    }


    @Given("^user has opened Mantis login page$")
    public void userHasOpenedMantisLoginPage() {
     loginPage=
       loginPage
              .openPage(LoginPage.class);
    }

    @Then("^user specify valid credentials and click on button Login$")
    public void userSpecifyValidCredentialsAndClickOnButtonLogin(List<User> userCredentials) {
      User user = userCredentials.stream().findFirst().get();
       viewPage =
          loginPage
                  .tryLoginWithCredentials(user.getUserName(), user.getPassword(), ViewPage.class);
       assertEquals(user.getUserName(), viewPage.getLoggedInAsText());

    }

    @Then("^user specify invalid credentials and click on button Login$")
    public void userSpecifyInvalidCredentialsAndClickOnButtonLogin(List<User> userInvalidCredentials) {
        User user = userInvalidCredentials.stream().findFirst().get();
            loginPage
                    .tryLoginWithCredentials(user.getUserName(), user.getPassword(), LoginPage.class);

    }

    @Then("^user click on Manage link$")
    public void userClickOnManageLink() {
    managePage =
                viewPage
                        .clickOnManageLink();
    }

    @Then("^user click Manage Projects$")
    public void userClickManageProjects() {
        manageProjectPage =
                managePage
                        .clickOnManageProjectLink();
    }

    @Then("^user click on button Create new Project$")
    public void userClickOnButtonCreateNewProject() {
        manageProjectPage
                .clickOnCreateNewProject();
    }

    @When("^user open new Project url$")
    public void userOpenNew_Project_url() {
      manageProjectPage =
        viewPage
           .openManageProjectPage();
    }


    @Then("^user specify project name and click on button Add Project$")
    public void userSpecifyProjectNameAndClickOnButtonAddProject() {
       manageProjectPage
               .inputProjectName(projectName)
               .clickOnAddProject();
       assertEquals("There is no such project", manageProjectPage.isProjectAdded(projectName), true);
       viewPage =
              new ViewPage()
               .openPage(ViewPage.class);
    }
}
