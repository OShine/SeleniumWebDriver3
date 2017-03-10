import org.openqa.selenium.Alert;
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

import java.util.concurrent.TimeUnit;

/**
 * Created by DenisShklyannik on 10.03.2017.
 */
public class Alerts {

    private WebDriver driver;
    private String baseUrl = "https://the-internet.herokuapp.com/javascript_alerts";

    @BeforeMethod
    public void beforeMethod() {

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffprofile = profile.getProfile("default");
        driver = new FirefoxDriver(ffprofile);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void firstAlert() throws InterruptedException {

        driver.get(baseUrl);

        WebElement simpleJSAlert = driver.findElement(By.xpath(".//*[@id='content']/div/ul/li[1]/button")); // first button .example>ul>li:nth-child(1)>button
        simpleJSAlert.click();

        Alert simpleAlert = driver.switchTo().alert();
        Assert.assertTrue(simpleAlert.getText().contains("I am a JS Alert"));
        Thread.sleep(2000); //This sleep is not necessary, just for demonstration
        simpleAlert.accept();

    }

    @Test
    public void secondAlert() throws InterruptedException {

        driver.get(baseUrl);

        WebElement confirmationJSAlert = driver.findElement(By.xpath(".//*[@id='content']/div/ul/li[2]/button")); // second button .example>ul>li:nth-child(2)>button
        confirmationJSAlert.click();

        Alert confirmationAlert = driver.switchTo().alert();
        Assert.assertTrue(confirmationAlert.getText().contains("I am a JS Confirm"));
        Thread.sleep(2000); //This sleep is not necessary, just for demonstration
        confirmationAlert.dismiss();

    }

    @Test
    public void thirdAlert() throws InterruptedException {

        driver.get(baseUrl);

        WebElement promptJSAlert = driver.findElement(By.xpath(".//*[@id='content']/div/ul/li[3]/button")); // third button .example>ul>li:nth-child(3)>button
        promptJSAlert.click();

        Alert promptAlert = driver.switchTo().alert();
        Assert.assertTrue(promptAlert.getText().contains("I am a JS prompt"));
        promptAlert.sendKeys("Accepting the alert");
        Thread.sleep(2000); //This sleep is not necessary, just for demonstration
        promptAlert.accept();

    }


    @AfterMethod
    public void afterMethod() {

        driver.quit();

    }

}