package Pages.com.DuckDuckGoPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage {

    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "search_form_input_homepage")
    public WebElement searchBox;

    @FindBy(id = "search_button_homepage")
    public WebElement searchButton;

    @FindBy(xpath = "//div[@class='results--main']//a[@class='result__a']")
    public List<WebElement> searchResults;

    @FindBy(xpath = "//form")
    public WebElement insideSearchBox;

}
