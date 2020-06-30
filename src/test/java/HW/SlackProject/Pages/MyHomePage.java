package HW.SlackProject.Pages;

import Utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyHomePage {

    public static WebDriver driver = Driver.getDriver();

    public MyHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "domain")
    public WebElement workSpace;

    @FindBy(id = "submit_team_domain")
    public WebElement continueButton;

    @FindBy(id = "email")
    public WebElement emailBox;

    @FindBy(id = "password")
    public WebElement passwordBox;

    @FindBy(id = "signin_btn")
    public WebElement singInButton;

    @FindBy(xpath = "//div[@data-team-id='TTP3PS9QD']")
    public WebElement messageBox;

    @FindBy(xpath = "//i[@type='paperplane-filled']")
    public WebElement sendButton;

    public void goToHomePage() {

        driver.get("https://app.slack.com/client/TTP3PS9QD/C0164SXRETU");

        workSpace.sendKeys("techtorialbatch4");
        continueButton.submit();
        emailBox.sendKeys("boiko.vol1@gmail.com");
        passwordBox.sendKeys("Audia8r8a6!");
        singInButton.submit();
    }

    public boolean isMessagePresent(String text) {
        try {
            WebElement message = driver.findElement(By.xpath("//*[text()='" + text + "']"));
            System.out.println(message.getText());
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

}
