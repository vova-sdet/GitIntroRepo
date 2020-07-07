package HW.SlackProject.Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

// Scenario 5 question
// How two run two scenarios with different tags
//        {
//        "ok": false,
//        "error": "cant_delete_message"
//        }

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/java/HW/SlackProject/Slack.feature",
        glue = "HW/SlackProject/StepDefinitions",
        plugin = {"rerun:target/rerun.txt",
                "json:target/cucumber.json", "junit:target/cucumber.xml",
                "pretty", "html:target/cucumber-html-report"},

        monochrome = false,
        dryRun = false,
        tags = "@five"
)

public class Runner {
}
