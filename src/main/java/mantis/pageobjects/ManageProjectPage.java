package mantis.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class ManageProjectPage extends Page<ManageProjectPage> {

    @FindBy(xpath = "//input[@value='Create New Project']")
    WebElement createNewProjectButton;

    @FindBy(xpath = "//input[@name='name']")
    WebElement projectNameInput;

    @FindBy(xpath = "//input[@value='Add Project']")
    WebElement addProjectButton;

    @FindBy(xpath = "//table//td[count(//tr//a[text()='Name'])]")
    List<WebElement> allProjectName;

    @FindBy(css = ".width100")
    WebElement tableProject;

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(createNewProjectButton);
    }

    @Override
    public String getPageUrl() {
        return "/manage_proj_page.php";
    }

    public ManageProjectPage clickOnCreateNewProject(){
        createNewProjectButton.click();
        return this;
    }

    public ManageProjectPage inputProjectName(String projectName){
        projectNameInput.sendKeys(projectName);
        return this;
    }

    public ManageProjectPage clickOnAddProject() {
        addProjectButton.click();
        return openPage(ManageProjectPage.class);
    }

    public boolean isProjectAdded(String projectName){
          allProjectName.stream()
             .anyMatch(project -> project.getText().equals(projectName));
          return true;
    }
}
