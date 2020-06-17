package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


    @RunWith(Cucumber.class)
    @CucumberOptions(
            plugin = {"rerun:target/rerun.txt",
            "json:target/cucumber.json", "junit:target/cucumber.xml",
            "pretty", "html:target/cucumber-html-report"},
            features = "src/test/resources/com.WebOrder",
            glue = "StepDefinitions/com/weborder",
            monochrome = false,
            dryRun = false
    )

    public class WebOrderRegressionRunner {
    }

