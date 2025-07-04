package EjadaRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/main/resources/features"},
        glue = {"EjadaStepDefinition", "EjadaHooks", "EjadaValidationAndScreenshots", "EjadaLocators", "EjadaUIUtils"},
        tags = "@Login",
        plugin = {"pretty",
                "junit:target/cucumber-reports/cucumber.xml",
                "html:target/cucumber-reports/cucumber-html-report.html",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/site/cucumber-pretty",
                "rerun:target/failed_scenarios.txt"},
        monochrome = true
)

public class EjadaTestRunner extends AbstractTestNGCucumberTests {
}
