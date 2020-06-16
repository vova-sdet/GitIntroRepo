package Pages.com.WebOrderPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /*
    WebElement loginButton = driver.findElement(By.id('loginButton");
    driver.navigate().refresh();
    loginButton.click();
    loginButton = driver.findElement(By.id('loginButton");
     */

    /*
    PageFactory.initElements(driver, LoginPage.class); -> it will solve the StaleElementException if the element is refreshed on the page
     */

    @FindBy(id = "ctl00_MainContent_username")
    public WebElement usernameBox;

    @FindBy(id = "ctl00_MainContent_password")
    public WebElement passwordBox;

    @FindBy(id = "ctl00_MainContent_login_button")
    public WebElement loginButton;

    @FindBy(id = "ctl00_MainContent_status")
    public WebElement errorMessage;
}
