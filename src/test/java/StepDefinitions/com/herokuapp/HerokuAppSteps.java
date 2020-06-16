package StepDefinitions.com.herokuapp;

import Pages.com.HerokuAppPages.IframeDocStringPage;
import Utils.BrowserUtils;
import Utils.ConfigReader;
import Utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

public class HerokuAppSteps {

    WebDriver driver = Driver.getDriver();
    IframeDocStringPage iframePage = new IframeDocStringPage(driver);

    @Given("the user on iframe page")
    public void the_user_on_iframe_page() {
        driver.navigate().to(ConfigReader.getProperties("herokuIframeUrl"));
        BrowserUtils.switchFrameByIndex(driver, 0);
    }

    @Then("the user enters the text")
    public void the_user_enters_the_text(String docString) {
        iframePage.textBox.sendKeys(docString);
    }
}
