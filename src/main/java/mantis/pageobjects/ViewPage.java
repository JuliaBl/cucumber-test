package mantis.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class ViewPage extends Page<ViewPage> {

    @FindBy(css = ".login-info-left .italic")
    WebElement loggedInAsText;

    @FindBy(linkText = "Logout")
    WebElement logOutLink;

    @FindBy(linkText = "Manage")
    WebElement manageLink;

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(loggedInAsText);
    }

    @Override
    public String getPageUrl() {
        return "/my_view_page.php";
    }

    public String getLoggedInAsText(){
        return  loggedInAsText.getText();
    }

    public LoginPage clickOnLogOut(){
        logOutLink.click();
        return new LoginPage();
    }

    public ManagePage clickOnManageLink() {
      manageLink.click();
      return new ManagePage().openPage(ManagePage.class);
    }
}
