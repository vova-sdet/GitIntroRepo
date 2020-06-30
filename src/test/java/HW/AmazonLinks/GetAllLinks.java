package HW.AmazonLinks;

import Utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetAllLinks {

    public static Set<String> getLinks() throws IOException, URISyntaxException {

        WebDriver driver = Driver.getDriver();
        driver.get("https://www.amazon.com/");
        List<WebElement> links = driver.findElements(By.tagName("a"));

        Set<String> uniqueLinks = new HashSet<>();

        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href != null && !href.equals("javascript: void(0)") && !href.equals("") && !href.endsWith("com/") && !href.contains("aboutamazon"))
                uniqueLinks.add(link.getAttribute("href"));
        }

        System.out.println(uniqueLinks.size());
        return uniqueLinks;
    }

}
