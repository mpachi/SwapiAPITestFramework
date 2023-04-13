package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.BranchFinder;
import pageobjects.HomePage;
import tests.BaseTest;

import java.io.IOException;

public class BranchFinderStepDef extends BaseTest {
    public HomePage homePage;
    public BranchFinder branchFinder;

    @Given("user is on Lloyds Banking Group's {string} page")
    public void user_is_on_lloyds_banking_group_s_page(String string) throws IOException {
        homePage = launchApplication();
    }

    @When("user clicks on {string}")
    public void user_clicks_on(String string) throws InterruptedException {
        branchFinder = homePage.clickOnBranchFinder();
    }

    @When("user enters {string} postcode")
    public void user_enters_postcode(String area) throws Exception {
        branchFinder.InputPostCodeAndSearch(area);
    }


    @Then("all nearest branches should be displayed")
    public void all_nearest_branches_should_be_displayed() {

    }

    @When("user clicks on {string} of {string} branch")
    public void user_clicks_on_of_branch(String link, String branchIndex) throws InterruptedException {
        branchFinder.selectBranch(link,branchIndex);
    }

    @Then("user is taken to {string} branch's page")
    public void user_is_taken_to_branch_s_page(String branch) {
    branchFinder.logBranchPhoneNumber(branch);
    }

}
