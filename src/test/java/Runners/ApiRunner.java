package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/resources/com.api/PetstoreRestAssured.feature",
        glue = "StepDefinitions/API",
        plugin = {"rerun:target/rerun.txt",
                "json:target/cucumber.json", "junit:target/cucumber.xml",
                "pretty", "html:target/cucumber-html-report"},
        dryRun = false,
        monochrome = false
)

public class ApiRunner {
}
