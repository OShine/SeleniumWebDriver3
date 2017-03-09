import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.common.base.Function;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    private WebDriver driver;
    private String baseUrl = "https://192.168.100.26/";

    @BeforeMethod
    public void beforeMethod() {

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffprofile = profile.getProfile("default");
        driver = new FirefoxDriver(ffprofile);

        //Task #4.1 Implicit Wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test(enabled = false)
    public void main() throws InterruptedException {

        driver.get(baseUrl);

        WebDriverWait wait = new WebDriverWait(driver, 10);

        // first task
        WebElement usernameElement = driver.findElement(By.cssSelector("#Username"));
        usernameElement.sendKeys("EugenBorisik");
        WebElement passwordElement = driver.findElement(By.cssSelector("#Password"));
        passwordElement.sendKeys("qwerty12345");
        //WebElement rememberElement = driver.findElement(By.cssSelector(".remember-chBox"));
        //rememberElement.click();

        Thread.sleep(2000); // Task 4.2  Thread.sleep - это третий тип ожидания. Первый вид - Явное (Impliced) и неявное (Explited) ожидание. Второй вид - кастомное ожидание.

        WebElement loginElement = driver.findElement(By.cssSelector("#SubmitButton"));
        loginElement.click();

        // second task
        WebElement classNameElement = driver.findElement(By.className("global-search"));
        WebElement searchByIdElement = driver.findElement(By.id("individualMenu"));
        WebElement partialLinkTextElement = driver.findElement(By.partialLinkText("Management USA"));
        List<WebElement> tagNameElements = driver.findElements(By.tagName("a"));
        WebElement linkTextElement = driver.findElement(By.linkText("csi.EugenBorisik"));
        WebElement xPathElement = driver.findElement(By.xpath("//div[@class='defaultWidgetYScrolling']"));
        // By.name locator is not available for this page

        // main part of test
        //WebElement signElement = driver.findElement(By.cssSelector(".sign-out-span"));
        WebElement signElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sign-out-span"))); // Task 4.3
        Assert.assertTrue(signElement.getText().contains("Sign Out"));

    }


    //Task #4.4 Implicit Wait
    @Test(enabled = false)
    public void secondCase() throws InterruptedException {

        driver.get(baseUrl);
        WebDriverWait waitSecondCase = new WebDriverWait(driver, 10);

        WebElement usernameElement = waitSecondCase.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#Username")));
        usernameElement.sendKeys("EugenBorisik");
        WebElement passwordElement = waitSecondCase.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#Password")));
        passwordElement.sendKeys("qwerty12345");
        WebElement loginElement = waitSecondCase.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#SubmitButton")));
        loginElement.click();

        WebElement signElement = waitSecondCase.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sign-out-span")));
        Assert.assertTrue(signElement.getText().contains("Sign Out"));

        //WebElement officeTabElement = waitSecondCase.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#officeMenu")));
        //officeTabElement.click();

    }

    //Task #4.4d    Go to office tab, wait for "Search by office" input to appear
    @Test
    public void thirdCase() throws InterruptedException {

        driver.get(baseUrl + "Office/Index");

        WebElement usernameElement = driver.findElement(By.cssSelector("#Username"));
        usernameElement.sendKeys("EugenBorisik");
        WebElement passwordElement = driver.findElement(By.cssSelector("#Password"));
        passwordElement.sendKeys("qwerty12345");
        WebElement loginElement = driver.findElement(By.cssSelector("#SubmitButton"));
        loginElement.click();

        Wait<WebDriver> waitThirdCase = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS).pollingEvery((long) 2.7, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

        WebElement searchByOfficeElement = waitThirdCase.until(new Function<WebDriver, WebElement>()  {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath(".//*[@id='input-search']"));
            }
        });
        Assert.assertTrue(searchByOfficeElement.isDisplayed());


    }

    @AfterMethod
    public void afterMethod() {

        driver.quit();

    }

}
