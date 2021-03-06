import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by DenisShklyannik on 09.03.2017.
 */
public class Frame {

    private WebDriver driver;
    private String baseUrl = "https://the-internet.herokuapp.com/iframe";

    @BeforeMethod
    public void beforeMethod() {

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffprofile = profile.getProfile("default");
        driver = new FirefoxDriver(ffprofile);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void firstCase() throws InterruptedException {

        driver.get(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement iFrameElement = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(iFrameElement);

        WebElement boardOnFrame = driver.findElement(By.xpath(".//*[@id='tinymce']/p"));
        boardOnFrame.clear();
        boardOnFrame.click();
        boardOnFrame.sendKeys("- Hello ");

        driver.switchTo().defaultContent();

        WebElement boltButton = driver.findElement(By.cssSelector(".mce-i-bold"));
        boltButton.click();

        driver.switchTo().frame(iFrameElement);

        boardOnFrame.click();
        boardOnFrame.sendKeys("world!");

        WebElement boardAssert = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#tinymce>p"))); // Task 4.3
        Assert.assertEquals(boardAssert.getText(), "- Hello \uFEFFworld!");

        WebElement boardStrongAssert = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#_mce_caret>strong"))); // Task 4.3
        Assert.assertEquals(boardStrongAssert.getText(),"world!");

    }

    @AfterMethod
    public void afterMethod() {

        driver.quit();

    }

}
