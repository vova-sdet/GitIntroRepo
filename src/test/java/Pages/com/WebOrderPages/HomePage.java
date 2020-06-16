package Pages.com.WebOrderPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ctl00_logout")
    public WebElement logoutButton;

    @FindBy(className = "login_info")
    public WebElement welcomeMessage;

    @FindBy(xpath = "//a[.='Order']")
    public WebElement orderButton;

    @FindBy(xpath = "//a[.='View all orders']")
    public WebElement allOrders;
}
