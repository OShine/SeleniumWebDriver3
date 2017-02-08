import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGSeleniumExample {
    public String baseUrl = "https://192.168.100.26/";
    public WebDriver driver = new FirefoxDriver();

    @Test(priority = 1)
    public  void verifyHomePageTitle() {
        driver.get(baseUrl);
        String expectedTitle = "Expected Title";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        driver.quit();
    }

}