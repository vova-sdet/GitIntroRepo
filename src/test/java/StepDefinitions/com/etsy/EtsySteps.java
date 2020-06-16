package StepDefinitions.com.etsy;

import Pages.com.EtsyPages.EtsyHomePage;
import Utils.ConfigReader;
import Utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class EtsySteps {

    WebDriver driver = Driver.getDriver();
    EtsyHomePage etsyHomePage = new EtsyHomePage(driver);

    @Given("the user goes to Etsy")
    public void the_user_goes_to_Etsy() {
        driver.navigate().to(ConfigReader.getProperties("etsyUrl"));
    }

    @When("the user search with {string} in Etsy")
    public void the_user_search_with_in_Etsy(String searchValue) {
        etsyHomePage.searchBox.sendKeys(searchValue);
        etsyHomePage.searchButton.click();
    }
    @Then("the user validate title {string} and {string} url")
    public void the_user_validate_title_and_url(String expectedTitle, String expectedUrlContains) {
        Assert.assertEquals(expectedTitle, driver.getTitle());
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrlContains));
    }
}
