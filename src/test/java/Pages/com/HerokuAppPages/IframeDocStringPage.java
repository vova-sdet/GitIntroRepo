package Pages.com.HerokuAppPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IframeDocStringPage {

    public IframeDocStringPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "tinymce")
    public WebElement textBox;
}
