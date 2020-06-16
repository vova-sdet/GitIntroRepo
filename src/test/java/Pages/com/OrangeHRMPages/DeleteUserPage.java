package Pages.com.OrangeHRMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteUserPage {

    public DeleteUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tbody//td[1]")
    public WebElement userCheckbox;

    @FindBy(id = "btnDelete")
    public WebElement deleteButton;

    @FindBy(id = "deleteConfModal")
    public WebElement popUpDeleteConfirm;

    @FindBy(id = "dialogDeleteBtn")
    public WebElement deleteOkButton;

    @FindBy(xpath = "//tbody//td")
    public WebElement textAfterDeletion;

}
