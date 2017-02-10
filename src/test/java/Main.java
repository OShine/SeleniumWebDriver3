import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by EugenBorisik on 2/8/2017.
 */
public class Main {

    public WebDriver driver;
    public String baseUrl = "https://192.168.100.26/";

    @BeforeMethod
    public void beforeMethod() {

//        FirefoxProfile profile = new FirefoxProfile();
//        profile.setAcceptUntrustedCertificates(true);
//        profile.setAssumeUntrustedCertificateIssuer(true);
//        driver = new FirefoxDriver(profile);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get(baseUrl);

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffprofile = profile.getProfile("QA");
        ffprofile.setAcceptUntrustedCertificates(true);
        ffprofile.setAssumeUntrustedCertificateIssuer(false);
        driver = new FirefoxDriver(ffprofile);
        driver.get(baseUrl);

    }

    @Test
    public void main() {


       // WebElement searchButton = driver.findElement(By.cssSelector("[name=\"btnK\"]"));
       // new WebDriverWait(driver, 15, 2300).until(ExpectedConditions.visibilityOf(searchButton));

    }

    @AfterMethod
    public void afterMethod() {

        driver.quit();

    }

}
