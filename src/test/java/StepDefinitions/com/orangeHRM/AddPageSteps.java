package StepDefinitions.com.orangeHRM;

import Pages.com.OrangeHRMPages.AddPage;
import Utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddPageSteps {

    WebDriver driver = Driver.getDriver();
    AddPage addPage = new AddPage(driver);
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @Given("the user click add button")
    public void the_user_click_add_button() {
        addPage.addButton.click();
    }

    @Then("the user select {string} user's role")
    public void the_user_select_user_s_role(String role) {
        Select select = new Select(addPage.userRole);
        select.selectByVisibleText(role);
    }

    @Then("the user select {string} as the employee name")
    public void the_user_select_as_the_employee_name(String name) {
        addPage.nameOfUser.sendKeys(name);
    }

    @Then("the user enter {string} as the username")
    public void the_user_enter_as_the_username(String username) {
        addPage.usernameBox.sendKeys(username);
    }

    @Then("the user select {string} status")
    public void the_user_select_status(String status) {
        Select select = new Select(addPage.userStatus);
        select.selectByVisibleText(status);
    }

    @Then("the user enter {string} password and confirm")
    public void the_user_enter_password_and_confirm(String password) {
        addPage.passwordBox.sendKeys(password);
        addPage.confirmPasswordBox.sendKeys(password);
    }

    @Then("the user click Save button")
    public void the_user_click_Save_button() {
        wait.until(ExpectedConditions.elementToBeClickable(addPage.saveButton));
        addPage.saveButton.click();
    }

    @Then("Validate {string} message")
    public void validate_message(String expectedMessage) {
        wait.until(ExpectedConditions.visibilityOf(addPage.successMessage));
        Assert.assertTrue(addPage.successMessage.isDisplayed());
        String actualMessage = addPage.successMessage.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }
}
