package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = "src/test/resources/com.WebOrder/NewOrderDataTable.feature",
            glue = "StepDefinitions/com/weborder",
            monochrome = false,
            dryRun = false
    )

    public class WebOrderRegressionRunner {
    }

