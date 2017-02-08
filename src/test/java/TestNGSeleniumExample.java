import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class TestNGSeleniumExample {
    private WebDriver driver = new FirefoxDriver();
    private String baseUrl;

    @BeforeMethod
    public void setUp() throws Exception {
        driver =  new InternetExplorerDriver();
        baseUrl = "http://EugenBorisik:qwerty12345@https://192.168.100.26/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitled() throws Exception {
        driver.get(baseUrl);

    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();

    }
}