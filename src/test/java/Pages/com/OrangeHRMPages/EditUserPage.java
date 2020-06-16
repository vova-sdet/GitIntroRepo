package Pages.com.OrangeHRMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EditUserPage {

    public EditUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "searchSystemUser_userName")
    public WebElement searchByUsernameBox;

    @FindBy(id = "searchBtn")
    public WebElement searchButton;

    @FindBy(xpath = "//tbody//tr")
    public List<WebElement> numOfSearchResult;

    @FindBy(xpath = "//tbody//td//a")
    public WebElement actualUsername;

    @FindBy(xpath = "//tbody//tr//td[3]")
    public WebElement actualRole;

    @FindBy(xpath = "//tbody//tr//td[4]")
    public WebElement actualName;

    @FindBy(xpath = "//tbody//tr//td[5]")
    public WebElement actualStatus;

}
