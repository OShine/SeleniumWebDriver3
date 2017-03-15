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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by DenisShklyannik on 09.03.2017.
 */
public class Login {

    private WebDriver driver;
    private String baseUrl = "https://192.168.100.26/";

    @DataProvider(name = "Authentication")

    public static Object[][] credentials() {

        return new Object[][] { { "", "" }, { "  ", ""}, { "EugenBorisik", "   "}};

    }

    @BeforeMethod
    public void beforeMethod() {

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffprofile = profile.getProfile("default");
        driver = new FirefoxDriver(ffprofile);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test(dataProvider = "Authentication")
    public void test(String sUsername, String sPassword) {

        driver.get(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#Username")));
        usernameElement.sendKeys(sUsername);

        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#Password")));
        passwordElement.sendKeys(sPassword);
        WebElement loginElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#SubmitButton")));
        loginElement.click();

        WebElement passwordWarning = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@title='Password is required']")));
        Assert.assertTrue(passwordWarning.getText().contains("Password is required"));

    }

    @AfterMethod
    public void afterMethod() {

        driver.quit();

    }

}
