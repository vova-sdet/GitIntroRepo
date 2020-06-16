package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources"},
        glue = "StepDefinitions",
        monochrome = true,
        dryRun = false,
        tags = {"@negative", "@smoke"}
)

public class SmokeTestRunner {
    // I want to run all feature file or scenarios which have a smoke and negative tags
}
