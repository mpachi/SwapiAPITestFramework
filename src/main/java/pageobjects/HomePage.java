package pageobjects;

import AbstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class HomePage extends AbstractComponent {
    WebDriver driver;
    @FindBy(xpath = "//span[text()='Branch Finder']")
    WebElement branchFinderLink;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goTo() {
        driver.get("https://www.lloydsbank.com");
    }

    public BranchFinder clickOnBranchFinder() throws InterruptedException {
        branchFinderLink.click();
        Thread.sleep(2000);
        return new BranchFinder(driver);
    }

}
