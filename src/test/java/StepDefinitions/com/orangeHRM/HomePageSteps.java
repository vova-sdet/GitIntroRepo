package StepDefinitions.com.orangeHRM;

import Pages.com.OrangeHRMPages.HomePage;
import Utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.NavigableSet;
import java.util.TreeSet;

public class HomePageSteps {

    WebDriver driver = Driver.getDriver();
    HomePage homePage = new HomePage(driver);

    @Given("the user click admin function")
    public void the_user_click_admin_function() {
        homePage.adminFunction.click();
    }

    @Then("the user click first checkbox next to the username")
    public void the_user_click_first_checkbox_next_to_the_username() {
        homePage.selectAllCheckBox.click();
        System.out.println("second " + homePage.selectAllCheckBox.isSelected());
    }

    @Then("validate all checkboxes are selected")
    public void validate_all_checkboxes_are_selected() {
        for (WebElement checkBox : homePage.allCheckBoxes) {
            Assert.assertTrue(checkBox.isSelected());
        }
    }

    @Then("the user double click on top of the username tab")
    public void the_user_click_on_top_of_the_username_times() {
        homePage.usernameTab.click();
        homePage.usernameTab.click();
    }

    @Then("validate all usernames are listed in descending order")
    public void validate_all_usernames_are_listed_in_descending_order() {

        TreeSet<String> userNamesSet = new TreeSet<>();

        for(WebElement name : homePage.userNames) {
            userNamesSet.add(name.getText());
        }

        NavigableSet<String> namesNavigableSet = userNamesSet.descendingSet();

        Object[] expectedNames = namesNavigableSet.toArray();

        for (int i = 0; i<homePage.userNames.size(); i++) {
            String actual = homePage.userNames.get(i).getText();
            String expected = expectedNames[i].toString();
            System.out.println(actual);
            System.out.println(expected);
            Assert.assertEquals(expected, actual);
        }
    }

}
