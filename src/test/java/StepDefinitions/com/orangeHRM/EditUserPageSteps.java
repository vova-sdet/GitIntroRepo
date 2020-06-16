package StepDefinitions.com.orangeHRM;

import Pages.com.OrangeHRMPages.AddPage;
import Pages.com.OrangeHRMPages.EditUserPage;
import Utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class EditUserPageSteps {

    WebDriver driver = Driver.getDriver();
    EditUserPage editUserPage = new EditUserPage(driver);
    AddPage addPage = new AddPage(driver);

    @Given("the user search with the {string} username")
    public void the_user_search_with_the_username(String username) {
        editUserPage.searchByUsernameBox.sendKeys(username);
        editUserPage.searchButton.click();
    }

    @Given("validate only one user displayed after search")
    public void validate_only_one_user_displayed_after_search() {
        int actualNumOfResults = editUserPage.numOfSearchResult.size();
        Assert.assertTrue(1==actualNumOfResults);
    }

    @Given("validate Username is {string}")
    public void validate_Username(String expectedUsername) {
        Assert.assertEquals(expectedUsername, editUserPage.actualUsername.getText());
    }

    @Given("validate User Role is {string}")
    public void validate_User_Role(String expectedRole) {
        Assert.assertEquals(expectedRole, editUserPage.actualRole.getText());
    }

    @Given("validate Employee Name is {string}")
    public void validate_Employee_Name(String expectedName) {
        Assert.assertEquals(expectedName, editUserPage.actualName.getText());
    }

    @Given("validate status of the employee is {string}")
    public void validate_status_of_the_employee(String expectedStatus) {
        Assert.assertEquals(expectedStatus, editUserPage.actualStatus.getText());
    }

    @Then("click on top the username")
    public void click_on_top_the_username() {
        editUserPage.actualUsername.click();
    }

    @Then("click edit button")
    public void click_edit_button() {
        addPage.saveButton.click();
    }

    @Then("change the Status to {string}")
    public void change_the_Status(String newStatus) {
        Select select = new Select(addPage.userStatus);
        select.selectByVisibleText(newStatus);
        addPage.saveButton.click();
    }

    @Then("validate {string} message")
    public void validate_message(String expectedMessage) {
        Assert.assertEquals(expectedMessage, addPage.successMessage.getText());
    }

    @Then("Search with the {string} username")
    public void search_with_the_username(String username) {
        editUserPage.searchByUsernameBox.sendKeys(username);
        editUserPage.searchButton.click();
    }

    @Then("validate status for the user is updated to {string}")
    public void validate_status_is_updated_for_the_user(String expectedNewStatus) {
        Assert.assertEquals(expectedNewStatus, editUserPage.actualStatus.getText());
    }

}
