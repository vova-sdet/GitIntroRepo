package Pages.com.WebOrderPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage {

    public OrderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    public WebElement productType;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    public WebElement quantityBox;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
    public WebElement customerName;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox2")
    public WebElement street;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox3")
    public WebElement city;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox4")
    public WebElement state;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox5")
    public WebElement zip;

    @FindBy(id = "ctl00_MainContent_fmwOrder_cardList_0")
    public WebElement visaCard;

    @FindBy(id = "ctl00_MainContent_fmwOrder_cardList_1")
    public WebElement masterCard;

    @FindBy(id = "ctl00_MainContent_fmwOrder_cardList_2")
    public WebElement americanExpress;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
    public WebElement cardNumber;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement cardExpiration;

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement processButton;

    @FindBy(xpath = "//strong")
    public WebElement successMessage;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_fmwOrder']//ol[1]//label")
    public List<WebElement> productHeaders;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_fmwOrder']//ol[2]//label")
    public List<WebElement> addressHeaders;

}
