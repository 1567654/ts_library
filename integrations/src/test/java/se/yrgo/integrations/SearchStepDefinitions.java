package se.yrgo.integrations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import se.yrgo.integrations.pos.SearchPage;
import se.yrgo.integrations.pos.StartPage;

import static org.junit.jupiter.api.Assertions.*;

public class SearchStepDefinitions {

    @When("the user navigates to the book search.")
    public void the_user_navigates_to_the_book_search() {
        WebDriver driver = GeneralStepDefinitions.getDriver();
        StartPage page = Utils.openStartPage(driver);
        page.search();
    }

    @Then("they can see the search form.")
    public void they_can_see_the_search_form() {
        WebDriver driver = GeneralStepDefinitions.getDriver();
        assertTrue(Utils.isSearchFormDisplayed(driver));
    }

    @Given("the user is on the search page.")
    public void the_user_is_on_the_search_page() {
        WebDriver driver = GeneralStepDefinitions.getDriver();
        Utils.openSearchPage(driver);
    }

    @When("the user performs a book search.")
    public void the_user_performs_a_book_search() {
        WebDriver driver = GeneralStepDefinitions.getDriver();
        Utils.searchBook(driver, "T-SQL Fundamentals", "Itzik Ben-Gan", "9781509302000");
    }

    @Then("they should see a list of matching books.")
    public void they_should_see_a_list_of_matching_books() {
        WebDriver driver = GeneralStepDefinitions.getDriver();
        assertTrue(Utils.hasResults(driver));
    }

    @When("the user performs a bad book search.")
    public void the_user_performs_a_bad_book_search() {
        WebDriver driver = GeneralStepDefinitions.getDriver();
        Utils.searchBook(driver, "", "", "");
    }

    @Then("they should see the text \"No books found\"")
    public void they_should_see_the_text_No_books_found() {
        WebDriver driver = GeneralStepDefinitions.getDriver();
        assertEquals("No books found", Utils.noBooks(driver));
    }
}
