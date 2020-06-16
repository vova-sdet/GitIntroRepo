package StepDefinitions.com.weborder;

import Pages.com.WebOrderPages.HomePage;
import Pages.com.WebOrderPages.LoginPage;
import Utils.ConfigReader;
import Utils.Driver;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginPageSteps {

    WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);

//    @Given("the demoUser enters username")
//    public void the_demoUser_enter_username() {
//        driver.navigate().to(ConfigReader.getProperties("weborderUrl"));
//        loginPage.usernameBox.sendKeys("Tester");
//    }
//
//    @When("the demoUser enters password")
//    public void the_demoUser_enters_password() {
//        loginPage.passwordBox.sendKeys("test");
//    }
//
//    @Then("the demoUser clicks the login button")
//    public void the_demoUser_clicks_the_login_button() {
//        loginPage.loginButton.click();
//    }

    @Before
    public void message() {
        System.out.println("Login Page Step class");
    }

    @BeforeStep
    public void beforeStep() {
        System.out.println("- Before step annotation");
    }

    @AfterStep
    public void afterStep() {
        System.out.println("+ After step annotation");
    }

    @Then("the demoUser validate the home page")
    public void the_demoUser_validate_the_home_page() {
        Assert.assertEquals(driver.getTitle(), "Web Orders");
        Assert.assertTrue(homePage.logoutButton.isDisplayed());
        Assert.assertTrue((homePage.welcomeMessage.getText().contains("Welcome, Tester!")));
    }

    @Given("the demoUser enters {string} username")
    public void the_demoUser_enters_username(String username) {
        driver.navigate().to(ConfigReader.getProperties("weborderUrl"));
        loginPage.usernameBox.sendKeys(username);
    }

    @When("the demoUser enters {string} password")
    public void the_demoUser_enters_password(String password) {
        loginPage.passwordBox.sendKeys(password);
        loginPage.loginButton.click();
    }

    @Then("the demoUser validate {string} text")
    public void the_demoUser_validate_text(String expected) {
        Assert.assertEquals(expected, loginPage.errorMessage.getText());
    }
}
