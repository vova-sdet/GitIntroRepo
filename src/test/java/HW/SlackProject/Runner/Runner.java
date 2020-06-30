package HW.SlackProject.Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/java/HW.SlackProject/Slack.feature",
        glue = "HW/SlackProject",
        plugin = {"rerun:target/rerun.txt",
                "json:target/cucumber.json", "junit:target/cucumber.xml",
                "pretty", "html:target/cucumber-html-report"},

        monochrome = false,
        dryRun = false,
        tags = "@one and @five"
)

public class Runner {
}
