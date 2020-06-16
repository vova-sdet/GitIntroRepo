package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/com.etsy/ScenarioOutlineEtsy.feature",
        glue = "StepDefinitions",
        dryRun = false,
        monochrome = false,
        tags = "@etsyOutline"
)

public class EtsyRegressionRunner {
}
