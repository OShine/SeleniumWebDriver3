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
public class Login {

    private WebDriver driver;
    private String baseUrl = "https://192.168.100.26/";

    @BeforeMethod
    public void beforeMethod() {

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffprofile = profile.getProfile("default");
        driver = new FirefoxDriver(ffprofile);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    //Task #4.5
    @Test
    public void firstCase() throws InterruptedException {

        driver.get(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#Username")));
        usernameElement.sendKeys("");
        WebElement loginElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#SubmitButton")));
        loginElement.click();

        WebElement usernameWarning = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@title='Username is required']")));
        Assert.assertTrue(usernameWarning.getText().contains("Username is required"));

    }

    @Test
    public void secondCase() throws InterruptedException {

        driver.get(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#Password")));
        passwordElement.sendKeys("");
        WebElement loginElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#SubmitButton")));
        loginElement.click();

        WebElement passwordWarning = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@title='Password is required']")));
        Assert.assertTrue(passwordWarning.getText().contains("Password is required"));

    }

    @Test
    public void thirdCase() throws InterruptedException {

        driver.get(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#Username")));
        usernameElement.sendKeys("EugenBorisik");
        WebElement loginElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#SubmitButton")));
        loginElement.click();

        WebElement passwordWarning = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@title='Password is required']")));
        Assert.assertTrue(passwordWarning.getText().contains("Password is required"));

    }

    @Test
    public void fourCase() throws InterruptedException {

        driver.get(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement usernameElement = driver.findElement(By.cssSelector("#Username"));
        usernameElement.sendKeys("EugenBorisik");
        WebElement passwordElement = driver.findElement(By.cssSelector("#Password"));
        passwordElement.sendKeys("qwerty12345");
        WebElement loginElement = driver.findElement(By.cssSelector("#SubmitButton"));
        loginElement.click();

        WebElement signElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sign-out-span"))); // Task 4.3
        Assert.assertTrue(signElement.getText().contains("Sign Out"));

    }


    @AfterMethod
    public void afterMethod() {

        driver.quit();

    }

}
