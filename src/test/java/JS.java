import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
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

public class JS {

    private WebDriver driverJS;
    private String baseUrl = "https://192.168.100.26/";

    @BeforeMethod
    public void beforeMethod() {

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffprofile = profile.getProfile("default");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:\\Users\\DenisShklyannik\\.m2\\repository\\com\\codeborne\\phantomjsdriver\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        driverJS = new PhantomJSDriver(caps);

        driverJS.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void HtmlUnit() throws Exception {

        driverJS.get(baseUrl);

        WebDriverWait wait = new WebDriverWait(driverJS, 10);

        WebElement usernameElement = driverJS.findElement(By.cssSelector("#Username"));
        usernameElement.sendKeys("EugenBorisik");
        WebElement passwordElement = driverJS.findElement(By.cssSelector("#Password"));
        passwordElement.sendKeys("qwerty12345");
        WebElement loginElement = driverJS.findElement(By.cssSelector("#SubmitButton"));
        loginElement.click();

        WebElement signElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sign-out-span")));

        File scrFile = ((TakesScreenshot)driverJS).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        try {
            FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshotJS.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(signElement.getText().contains("Sign Out"));
    }


    @AfterMethod
    public void tearDown() {
        driverJS.quit();
    }
}
