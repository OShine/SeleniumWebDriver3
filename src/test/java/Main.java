import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    private WebDriver driver;
    private String baseUrl = "https://192.168.100.26/";

    @BeforeMethod
    public void beforeMethod() {

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffprofile = profile.getProfile("certificateIssue");
        driver = new FirefoxDriver(ffprofile);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void main() {

        driver.get(baseUrl);
        WebElement usernameElement = driver.findElement(By.cssSelector("#Username"));
        usernameElement.sendKeys("EugenBorisik");
        WebElement passwordElement = driver.findElement(By.cssSelector("#Password"));
        passwordElement.sendKeys("qwerty12345");
        WebElement rememberElement = driver.findElement(By.cssSelector(".remember-chBox"));
        rememberElement.click();
        WebElement loginElement = driver.findElement(By.cssSelector("#SubmitButton"));
        loginElement.click();

        WebElement classNameElement = driver.findElement(By.className("global-search"));
        WebElement searchByIdElement = driver.findElement(By.id("individualMenu"));
        WebElement partialLinkTextElement = driver.findElement(By.partialLinkText("Management USA"));
        List<WebElement> tagNameElements = driver.findElements(By.tagName("a"));
        WebElement linkTextElement = driver.findElement(By.linkText("csi.EugenBorisik"));
        WebElement xPathElement = driver.findElement(By.xpath("//div[@class='defaultWidgetYScrolling']"));

        WebElement signElement = driver.findElement(By.cssSelector(".sign-out-span"));
        Assert.assertTrue(signElement.getText().contains("Sign Out"));

        // name locator is not available for this page



    }

    @AfterMethod
    public void afterMethod() {

        driver.quit();

    }

}
