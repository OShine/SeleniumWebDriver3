import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class phantomJS {

    private WebDriver driverJS;

    private static final String BASE_URL = "https://mail.ru";
    private static final String USERNAME = "seleniumtests10@mail.ru";
    private static final String PASSWORD = "060788avavav";
    private static final By MAILBOX_LOGIN = By.id("mailbox__login");
    private static final By MAILBOX_PASSWORD = By.id("mailbox__password");
    private static final By MAILBOX_AUTH_BUTTON = By.id("mailbox__auth__button");
    private static final By COMPOSE_BUTTON = By.cssSelector("[data-name=\"compose\"]>span");
    private static final By LOGOUT_BUTTON = By.cssSelector("#PH_logoutLink");
    private static final String COMPOSE_BUTTON_TEXT = "Написать письмо";
    private static final String AUTH_BUTTON_TEXT = "Войти";

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
    public void phantomJS() throws Exception {

        driverJS.get(BASE_URL);

        WebElement usernameElement = driverJS.findElement(MAILBOX_LOGIN);
        usernameElement.sendKeys(USERNAME);
        WebElement passwordElement = driverJS.findElement(MAILBOX_PASSWORD);
        passwordElement.sendKeys(PASSWORD);
        WebElement loginElement = driverJS.findElement(MAILBOX_AUTH_BUTTON);
        loginElement.click();

        File scrFile = ((TakesScreenshot)driverJS).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        try {
            FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshotJS.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(driverJS.findElement(COMPOSE_BUTTON).getText(), COMPOSE_BUTTON_TEXT);
    }

    @AfterMethod
    public void tearDown() {
        driverJS.quit();
    }
}
