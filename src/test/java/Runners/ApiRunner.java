package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/resources/com.api/CreatePet.feature",
        glue = "StepDefinitions/API",
        tags = "@api",
        plugin = {"rerun:target/rerun.txt",
                "json:target/cucumber.json", "junit:target/cucumber.xml",
                "pretty", "html:target/cucumber-html-report"}
)

public class ApiRunner {
}
