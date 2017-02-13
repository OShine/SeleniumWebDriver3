import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by EugenBorisik on 2/8/2017.
 */
public class Main {

    public WebDriver driver;
    public String baseUrl = "https://192.168.100.26/";

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
        WebElement passwordElement = driver.findElement(By.cssSelector("#Password"));
        WebElement rememberElement = driver.findElement(By.cssSelector(".remember-chBox"));
        WebElement loginElement = driver.findElement(By.cssSelector("#SubmitButton"));
        WebElement signElement = driver.findElement(By.cssSelector(".sign-out-span"));

        usernameElement.sendKeys("EugenBorisik");
        passwordElement.sendKeys("qwerty12345");
        rememberElement.click();
        loginElement.click();
        Assert.assertTrue(signElement.getText().contains("Sign Out"));

    }

    @AfterMethod
    public void afterMethod() {

        driver.quit();

    }

}
