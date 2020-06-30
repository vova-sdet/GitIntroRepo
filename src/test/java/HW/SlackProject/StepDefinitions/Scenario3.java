package HW.SlackProject.StepDefinitions;

import HW.SlackProject.Pages.MyHomePage;
import HW.SlackProject.Util.SlackConfigReader;
import Utils.Driver;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class Scenario3 {

    WebDriver driver = Driver.getDriver();
    MyHomePage slackPage = new MyHomePage(driver);

    @Given("the user sends message to slack with Selenium WebDriver in UI")
    public void the_user_sends_message_to_slack_with_Selenium_WebDriver_in_UI() {

        slackPage.goToHomePage();
        slackPage.messageBox.sendKeys(SlackConfigReader.getProperties("message"));
        slackPage.sendButton.click();
    }

}
