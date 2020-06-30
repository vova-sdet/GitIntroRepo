package HW.SlackProject.StepDefinitions;

import HW.SlackProject.Pages.MyHomePage;
import HW.SlackProject.Util.SlackConfigReader;
import Utils.Driver;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class Scenario2 {

    WebDriver driver = Driver.getDriver();
    MyHomePage slackPage = new MyHomePage(driver);

    @Then("the user verifies the message with Selenium WebDriver in UI")
    public void the_user_verifies_the_message_with_Selenium_WebDriver_in_UI() throws InterruptedException {

        if (!driver.getCurrentUrl().contains("client")) {
            slackPage.goToHomePage();
        }

        Assert.assertTrue(slackPage.isMessagePresent(SlackConfigReader.getProperties("message")));
    }
}
