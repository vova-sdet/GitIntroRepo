package StepDefinitions.com.orangeHRM;

import Pages.com.OrangeHRMPages.LoginPage;
import Utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginPageSteps {

    WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage(driver);

    @Given("the user enters {string} username")
    public void the_user_enters_username(String username) {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.usernameBox.sendKeys(username);
    }

    @Then("the the user enters {string} password")
    public void the_the_user_enters_password(String password) {
        loginPage.passwordBox.sendKeys(password);
    }

    @Then("the user click login button")
    public void the_user_click_login_button() {
        loginPage.loginButton.submit();
    }

    @Then("the user validates title of the page is {string}")
    public void the_user_validates_title_of_the_page_is(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }
}
