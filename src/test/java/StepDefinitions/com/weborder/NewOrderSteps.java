package StepDefinitions.com.weborder;

import Pages.com.WebOrderPages.AllOrdersPage;
import Pages.com.WebOrderPages.HomePage;
import Pages.com.WebOrderPages.OrderPage;
import Utils.BrowserUtils;
import Utils.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NewOrderSteps {

    WebDriver driver = Driver.getDriver();
    HomePage homePage = new HomePage(driver);
    OrderPage orderPage = new OrderPage(driver);
    AllOrdersPage allOrdersPage = new AllOrdersPage(driver);

    @Then("the user enter product info {string} and {string}")
    public void the_user_enter_product_info_and(String product, String quantity) {
        homePage.orderButton.click();
        Select select = new Select(orderPage.productType);
        select.selectByVisibleText(product);
        orderPage.quantityBox.clear();
        orderPage.quantityBox.sendKeys(quantity);
    }

    @Then("the user enters address info {string}, {string}, {string}, {string}, {string}")
    public void the_user_enters_address_info(String name, String street, String city, String state, String zipCode) {
        orderPage.customerName.sendKeys(name);
        orderPage.street.sendKeys(street);
        orderPage.city.sendKeys(city);
        orderPage.state.sendKeys(state);
        orderPage.zip.sendKeys(zipCode);
    }

    @Then("the user enters payment info {string}, {string}, {string}")
    public void the_user_enters_payment_info(String cardType, String cardNumber, String cardExpirationDate) {

        switch (cardType) {
            case "Visa":
                orderPage.visaCard.click();
                break;
            case "MasterCard":
                orderPage.masterCard.click();
                break;
            case "American Express":
                orderPage.americanExpress.click();
        }

        orderPage.cardNumber.sendKeys(cardNumber);
        orderPage.cardExpiration.sendKeys(cardExpirationDate);
        orderPage.processButton.click();
    }

    @Then("the user validate success message")
    public void the_user_validate_success_message() {
        Assert.assertTrue(orderPage.successMessage.isDisplayed());
    }

    @Then("the user validate new order details {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void the_user_validate_new_order_details(String productName, String quantity, String name, String address, String city, String state, String zipcode, String cardType, String cardNum, String expirationDate) {
        homePage.allOrders.click();
        List<WebElement> orderDetails = allOrdersPage.newOrderDetails;
        Assert.assertEquals(orderDetails.get(1).getText(), name);
        Assert.assertEquals(orderDetails.get(2).getText(), productName);
        Assert.assertEquals(orderDetails.get(3).getText(), quantity);
        String today = BrowserUtils.todaysDate("MM/dd/yyyy");
        Assert.assertEquals(orderDetails.get(4).getText(), today);
        Assert.assertEquals(orderDetails.get(5).getText(), address);
        Assert.assertEquals(orderDetails.get(6).getText(), city);
        Assert.assertEquals(orderDetails.get(7).getText(), state);
        Assert.assertEquals(orderDetails.get(8).getText(), zipcode);
        Assert.assertEquals(orderDetails.get(9).getText(), cardType);
        Assert.assertEquals(orderDetails.get(10).getText(), cardNum);
        Assert.assertEquals(orderDetails.get(11).getText(), expirationDate);
    }

    @When("the user goes to the new order page")
    public void the_user_goes_to_the_new_order_page() {
        homePage.orderButton.click();
    }

    @When("the user validate the product headers")
    public void the_user_validate_the_product_headers(List<String> expectedProductHeaders) {

        List<String> actualProductHeaders = new ArrayList<>();
        List<WebElement> headers = orderPage.productHeaders;
        for(WebElement header : headers) {
            actualProductHeaders.add(header.getText());
        }

        Assert.assertEquals(expectedProductHeaders, actualProductHeaders);
    }

    @When("the user validate the address headers")
    public void the_user_validate_the_address_headers(List<String> expectedAddressHeaders) {
        List<String> actualAddressHeaders = new ArrayList<>();
        List<WebElement> headers = orderPage.addressHeaders;
        for(WebElement header : headers) {
            actualAddressHeaders.add(header.getText());
        }

        Assert.assertEquals(expectedAddressHeaders, actualAddressHeaders);
    }

}
