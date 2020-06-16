package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BrowserUtils {
    public static void switchWindowByTitle(WebDriver driver,String targetTitle){
        Set<String> ids=driver.getWindowHandles();
        for(String id: ids){
            if(!driver.getTitle().equals(targetTitle)){
                driver.switchTo().window(id);
            }
        }
    }

    public static void switchWindowByUrl(WebDriver driver, String url){
        Set<String> ids=driver.getWindowHandles();
        for(String id:ids){
            if(!driver.getCurrentUrl().contains(url)){
                driver.switchTo().window(id);
            }
        }
    }

    public static void closeWindows(WebDriver driver, String parentId){
        Set<String> ids=driver.getWindowHandles();
        for(String id:ids){
            if(!id.equals(parentId)){
                driver.switchTo().window(id);
                driver.close();
            }
        }
    }

    // create a method for fluent wait
    public static WebElement fluentWait(WebDriver driver, long totalSeconds, long frequency, By locator) {

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(totalSeconds))
                .pollingEvery(Duration.ofSeconds(frequency))
                .ignoring(RuntimeException.class);

        return wait.until(driver1 -> driver1.findElement(locator));
    }

    public static WebElement visibilityOf(WebDriver driver, long seconds, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);

        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement visibilityOfElementLocated(WebDriver driver, long seconds, By locator) {

        WebDriverWait wait = new WebDriverWait(driver, seconds);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static String takeScreenshot() {

        // First we cast our driver to (TakeScreenshot) then we get the screenshot as FILE
        File src = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
        // We need the destination to store all my screenshots
        // System.getProperty("user.dir") -> will give the project (file) location
        // System.currentTimeMillis() helps us to give unique name for my screenshot
        String destination = System.getProperty("user.dir") + "/Screenshot//" + System.currentTimeMillis() + ".png";
        File des = new File(destination);

        try {
            // .copyFile will get the file from source and store to the destination
            FileUtils.copyFile(src, des);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return destination;
    }

    public static String todaysDate(String formatType) {

        LocalDate today = LocalDate.now(); // 2021-05-26
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern(formatType);

        return newFormat.format(today);
    }

    public static void switchFrameByIndex(WebDriver driver, int index) {
        driver.switchTo().frame(index);
    }

    public static void switchFrameElementLocator(WebDriver driver, By locator) {
        driver.switchTo().frame(driver.findElement(locator));
    }

    public static void switchFrameByWebElement(WebDriver driver, WebElement element) {
        driver.switchTo().frame(element);
    }

    public static List<String> getTextOfElements(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();
        for (WebElement element : elements) {
            texts.add(element.getText().trim());
        }

        return texts;
    }

    public static void selectByVisibleText(WebElement element, String visibleText) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    public static void selectByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

}
