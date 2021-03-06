package mantis.pageobjects;

import mantis.utils.Config;
import mantis.utils.PropertiesLoader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.NoSuchElementException;
import static mantis.setup.SeleniumDriver.getDriver;

public abstract class Page<T> {
    private static final Config config = PropertiesLoader.getConfig();
    private WebDriver driver = getDriver();
    private static final String BASE_URL = config.getUrl();
    private static final int LOAD_TIMEOUT = 30;
    private static final int REFRESH_RATE = 2;

    public T openPage(Class<T> clazz){
        T page = PageFactory.initElements(driver, clazz);
        driver.get(BASE_URL + getPageUrl());
        waitForDocStateReady();
        ExpectedCondition pageLoadCondition = ((Page) page).getPageLoadCondition();
        waitForPageToLoad(pageLoadCondition);
        return page;
    }

    private void waitForPageToLoad(ExpectedCondition pageLoadCondition){
        Wait wait = new FluentWait(getDriver())
                .withTimeout(Duration.ofSeconds(LOAD_TIMEOUT))
                .pollingEvery(Duration.ofSeconds(REFRESH_RATE))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        wait.until(pageLoadCondition);
    }

    protected void waitForDocStateReady(){
        new WebDriverWait(driver, 50).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Provides condition when page can be considered as fully loaded.
     *
     * @return
     */
    protected abstract ExpectedCondition getPageLoadCondition();

    /**
     * Provides page relative URL/
     *
     * @return
     */
    public abstract String getPageUrl();

}
