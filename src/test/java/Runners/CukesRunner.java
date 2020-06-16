package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = "src/test/resources/com.duckduckgo/SearchBoxTest.feature",
            glue = "StepDefinitions/com/duckduckgo",
            monochrome = false,
            dryRun = false,
            tags = "@smoke and @techtorial1 and @tech"
    )

    public class CukesRunner {
}
