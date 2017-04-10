import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HeadlessTesting {

    private WebDriver driver;
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
        driver = new FirefoxDriver(ffprofile);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void Headless() throws InterruptedException {

        driver.get(BASE_URL);

        WebElement usernameElement = driver.findElement(MAILBOX_LOGIN);
        usernameElement.sendKeys(USERNAME);
        WebElement passwordElement = driver.findElement(MAILBOX_PASSWORD);
        passwordElement.sendKeys(PASSWORD);
        WebElement loginElement = driver.findElement(MAILBOX_AUTH_BUTTON);
        loginElement.click();

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        try {
            FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshotHeadless.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(driver.findElement(COMPOSE_BUTTON).getText(), COMPOSE_BUTTON_TEXT);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();

    }
}
