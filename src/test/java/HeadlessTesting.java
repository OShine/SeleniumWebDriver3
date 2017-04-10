import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HeadlessTesting {

    private WebDriver driver;
    private WebDriver driverJS;
    private String baseUrl = "https://192.168.100.26/";

    @BeforeMethod
    public void beforeMethod() {

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffprofile = profile.getProfile("default");
        driver = new FirefoxDriver(ffprofile);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test(enabled = false)
    public void Headless() throws InterruptedException {

        driver.get(baseUrl);

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement usernameElement = driver.findElement(By.cssSelector("#Username"));
        usernameElement.sendKeys("EugenBorisik");
        WebElement passwordElement = driver.findElement(By.cssSelector("#Password"));
        passwordElement.sendKeys("qwerty12345");
        WebElement loginElement = driver.findElement(By.cssSelector("#SubmitButton"));
        loginElement.click();

        WebElement signElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sign-out-span")));

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        try {
            FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(signElement.getText().contains("Sign Out"));
    }

    @Test
    public void HtmlUnit() throws InterruptedException {

        driver.quit();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability("TakeScreenshot", true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_PATH_PROPERTY, "C:\\Users\\DenisShklyannik\\.m2\\repository\\com\\codeborne\\phantomjsdriver\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        driverJS = new PhantomJSDriver(caps);

        driverJS.get(baseUrl);

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement usernameElement = driver.findElement(By.cssSelector("#Username"));
        usernameElement.sendKeys("EugenBorisik");
        WebElement passwordElement = driver.findElement(By.cssSelector("#Password"));
        passwordElement.sendKeys("qwerty12345");
        WebElement loginElement = driver.findElement(By.cssSelector("#SubmitButton"));
        loginElement.click();

        WebElement signElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sign-out-span")));

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshotJS.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(signElement.getText().contains("Sign Out"));
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
