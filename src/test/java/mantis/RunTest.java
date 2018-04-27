package mantis;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/htmlreports"},
        glue = {"mantis"},
        features = {"src/test/resources/mantis"}
)
public class RunTest {

}
