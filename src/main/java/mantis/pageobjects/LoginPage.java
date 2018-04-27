package mantis.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends Page<LoginPage> {

    @FindBy(name = "username")
    WebElement inputUserName;

    @FindBy(name = "password")
    WebElement inputPassword;

    @FindBy(css = ".button")
    WebElement buttonLogin;

    @FindBy(css = "font")
    WebElement errorMessageWhileLogin;

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(buttonLogin);
    }

    @Override
    public String getPageUrl() {
        return "/login_page.php";
    }

    private LoginPage inputUserNameAndPassword(String userName, String password){
      inputUserName.sendKeys(userName);
      inputPassword.sendKeys(password);
      return this;
    }

    private LoginPage clickButtonLogin(){
        buttonLogin.click();
        return this;
    }

    public <T extends Page<T>> T tryLoginWithCredentials(String userName, String password, Class<T> tClass){
       inputUserNameAndPassword(userName, password)
               .clickButtonLogin();
    try {
        if(tClass == LoginPage.class){
            return  tClass.getConstructor().newInstance();
        }
        else {
            return tClass.getConstructor().newInstance().openPage(tClass);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getErrorMessageForInvalidCredentials(){
        return  errorMessageWhileLogin.getText();
    }
}
