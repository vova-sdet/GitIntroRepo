package Pages.com.OrangeHRMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "menu_admin_viewAdminModule")
    public WebElement adminFunction;

    @FindBy(id = "ohrmList_chkSelectAll")
    public WebElement selectAllCheckBox;

    @FindBy(xpath = "//input[@name='chkSelectRow[]']")
    public List<WebElement> allCheckBoxes;

    @FindBy(xpath = "//a[.='Username']")
    public WebElement usernameTab;

    @FindBy(xpath = "//td[@class='left'][3]")
    public List<WebElement> userNames;

}
