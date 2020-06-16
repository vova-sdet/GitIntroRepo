package Pages.com.OrangeHRMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddPage {

    public AddPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "btnAdd")
    public WebElement addButton;

    @FindBy(id = "systemUser_userType")
    public WebElement userRole;

    @FindBy(id = "systemUser_employeeName_empName")
    public WebElement nameOfUser;

    @FindBy(id = "systemUser_userName")
    public WebElement usernameBox;

    @FindBy(id = "systemUser_status")
    public WebElement userStatus;

    @FindBy(id = "systemUser_password")
    public WebElement passwordBox;

    @FindBy(id = "systemUser_confirmPassword")
    public WebElement confirmPasswordBox;

    @FindBy(id = "btnSave")
    public WebElement saveButton;

    @FindBy(xpath = "//div[@class='message success fadable']")
    public WebElement successMessage;

}
