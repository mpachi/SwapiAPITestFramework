package tests;

import Utils.Utils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.HomePage;

import java.io.IOException;

public class BaseTest extends Utils {
    public WebDriver driver;
    public HomePage homePage;
    public WebDriver initializeDriver() throws IOException {

        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :getGlobalValue("browser");
        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));//full screen
        }
        return driver;
    }

    public HomePage launchApplication() throws IOException{
        driver = initializeDriver();
        homePage = new HomePage(driver);
        homePage.goTo();
        return homePage;
    }
}
