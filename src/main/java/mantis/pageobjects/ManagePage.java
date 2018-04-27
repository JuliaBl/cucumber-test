package mantis.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ManagePage extends Page<ManagePage> {

    @FindBy(linkText = "Manage Projects")
    WebElement manageProjectLink;

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(manageProjectLink);
    }

    @Override
    public String getPageUrl() {
        return "/manage_overview_page.php";
    }

    public ManageProjectPage clickOnManageProjectLink() {
        manageProjectLink.click();
        return new ManageProjectPage().openPage(ManageProjectPage.class);
    }
}
