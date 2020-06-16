package StepDefinitions.com.orangeHRM;

import Pages.com.OrangeHRMPages.DeleteUserPage;
import Utils.Driver;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class DeleteUserPageSteps {

    WebDriver driver = Driver.getDriver();
    DeleteUserPage deleteUserPage = new DeleteUserPage(driver);

    @Then("the user click the checkbox for the found user")
    public void the_user_click_the_checkbox_for_the_found_user() {
        deleteUserPage.userCheckbox.click();
    }

    @Then("the user click delete button")
    public void the_user_click_delete_button() {
        deleteUserPage.deleteButton.click();
    }

    @Then("Validate popup is displayed")
    public void validate_popup_is_displayed() {
        Assert.assertTrue(deleteUserPage.popUpDeleteConfirm.isDisplayed());
    }

    @Then("the user click OK button")
    public void the_user_click_OK_button() {
        deleteUserPage.deleteOkButton.click();
    }

    @Then("validate {string} text")
    public void validate_text(String expectedText) {
        Assert.assertEquals(expectedText, deleteUserPage.textAfterDeletion.getText());
    }
}
