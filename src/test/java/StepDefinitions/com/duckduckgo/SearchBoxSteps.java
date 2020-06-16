package StepDefinitions.com.duckduckgo;

import Pages.com.DuckDuckGoPages.SearchPage;
import Utils.ConfigReader;
import Utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchBoxSteps {

    WebDriver driver = Driver.getDriver();
    SearchPage searchPage;

    @Given("the user goes to the duckduckgo")
    public void the_user_goes_to_the_duckduckgo() {
        searchPage = new SearchPage(driver);
        driver.get(ConfigReader.getProperties("url"));
    }

    @When("the user send the Selenium keyword")
    public void the_user_send_the_Selenium_keyword() {
        searchPage.searchBox.sendKeys(ConfigReader.getProperties("searchValue"));
    }

    @Then("the user click search button")
    public void the_user_click_search_button() {
        searchPage.searchButton.click();

    }

    @Then("the user validate title contains the selenium keyword")
    public void the_user_validate_title_contains_the_selenium_keyword() {
        String actualTitle = driver.getTitle();
        String expectedKeyword = ConfigReader.getProperties("searchValue");
        Assert.assertTrue(actualTitle.contains(expectedKeyword));
    }

    @Then("the user validate url contains the selenium keyword")
    public void the_user_validate_url_contains_the_selenium_keyword() {
        String actualUrl = driver.getCurrentUrl();
        String expectedKeyword = ConfigReader.getProperties("searchValue");
        Assert.assertTrue(actualUrl.contains(expectedKeyword));
    }

    @Then("the user validate all results contain selenium keyword")
    public void the_user_validate_all_results_contain_selenium_keyword() {
        for (WebElement element : searchPage.searchResults) {
            String actualResult = element.getText();
            String expectedKeyword = ConfigReader.getProperties("searchUpperCase");
            Assert.assertTrue(actualResult.contains(expectedKeyword));
        }
    }

    @Given("the user search with {string}")
    public void the_user_search_with(String value) {
        //searchPage.insideSearchBox.clear();
        searchPage.searchBox.sendKeys(value);
    }

    @When("the user validate title contains {string}")
    public void the_user_validate_title_contains(String value) {
        String actualTitle = driver.getTitle();
        String expectedKeyword = value;
        Assert.assertTrue(actualTitle.contains(expectedKeyword));
    }

    @When("the user validate all results contains {string}")
    public void the_user_validate_all_results_contains(String value) {
        for (WebElement element : searchPage.searchResults) {
            String actualResult = element.getText();
            String expectedKeyword = value;
            Assert.assertTrue(actualResult.contains(expectedKeyword));
        }
    }

    @When("the user validate number of result is {int}")
    public void the_user_validate_number_of_result_is(Integer int1) {
        Integer expectedNumOfResults = int1;
        Integer actualNumOfResults = searchPage.searchResults.size();
        Assert.assertEquals(expectedNumOfResults, actualNumOfResults);
    }


}
