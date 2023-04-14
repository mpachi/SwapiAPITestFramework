package pageobjects;

import AbstractComponent.AbstractComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BranchFinder extends AbstractComponent {
    WebDriver driver;

    @FindBy(css = "input.search-input")
    WebElement postcodeSearchTextBox;

    @FindBy(css = "button.search-button")
    WebElement searchPostCodeButton;

    @FindBy(css = "li.ResultList-item")
     List<WebElement> allBranches;

    @FindBy(id = "phone-main")
    WebElement branchPhone;

    @FindBy(className = "ResultSummary")
    WebElement resultSummary;

    String basePath = System.getProperty("user.dir") + "/src/test/java";
    private static Logger logger= LogManager.getLogger();
    public BranchFinder(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPostcodefromJson(String area) throws Exception {
        String fileName=basePath+"/resources/postcodes.json";
        String json=readFileAsString(fileName);
        String postCode=getJsonPath(json,area);
        System.out.println(postCode);
        return postCode;

    }
    public void InputPostCodeAndSearch(String area) throws Exception {
        postcodeSearchTextBox.sendKeys(getPostcodefromJson(area));
        Thread.sleep(2000);
        searchPostCodeButton.click();
        waitForWebElementToAppear(resultSummary);
    }
    public void verifyResultSummary() throws Exception {
        waitForWebElementToAppear(resultSummary);
    }


    public void selectBranch(String link,String branchIndex) throws InterruptedException {
        WebElement branch = null;
        if (branchIndex.equalsIgnoreCase("first")) {
            branch = allBranches.get(0);
        }
        branch.click();
        WebElement viewBranch=branch.findElement(By.cssSelector("a.Teaser-ctaLink"));
        waitForWebElementToAppear(viewBranch);
        viewBranch.click();
    }

    public void logBranchPhoneNumber(String branch){
        waitForWebElementToAppear(branchPhone);
        System.out.println("Branch Phone number: "+branchPhone.getText());
        logger.info("Branch Phone No. "+branchPhone.getText());
        driver.close();
    }

}
