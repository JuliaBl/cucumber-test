package mantis.setup;

import mantis.utils.Config;
import mantis.utils.PropertiesLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.IOException;
import java.net.URL;


public class SeleniumDriver {
    protected static final Config config = PropertiesLoader.getConfig();
    private static WebDriver driver;

    private SeleniumDriver(){

    }

    public static synchronized WebDriver getDriver()  {
       if(driver == null) {
           if (!Boolean.parseBoolean(config.isRemote())) {
                     driver = new ChromeDriver();
                   System.out.println("Driver is initiated");
           } else {
               DesiredCapabilities capabilities = DesiredCapabilities.chrome();
               try {
                   driver = new RemoteWebDriver(new URL(config.getRemoteUrl()), capabilities);
                   System.out.println("Driver is initiated");
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
        driver.manage().window().maximize();
        return driver;
    }

    public static void close() {
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
