package se.yrgo.integrations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import se.yrgo.integrations.pos.SearchPage;
import se.yrgo.integrations.pos.StartPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static StartPage openStartPage(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://frontend");
        return new StartPage(driver);
    }

    public static SearchPage openSearchPage(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://frontend/search");
        return new SearchPage(driver);
    }

    public static boolean isSearchFormDisplayed(WebDriver driver) {
        return driver.findElement(By.cssSelector("section > form")).isDisplayed();
    }

    public static void searchBook(WebDriver driver, String title, String author, String isbn) {
        List<WebElement> inputs = driver.findElements(By.cssSelector("form > input"));
        WebElement titleInput = inputs.get(0);
        WebElement authorInput = inputs.get(1);
        WebElement isbnInput = inputs.get(2);

        titleInput.sendKeys(title);
        authorInput.sendKeys(author);
        isbnInput.sendKeys(isbn);

        WebElement searchBtn = driver.findElement(By.cssSelector("form > .btn.btn-primary"));
        searchBtn.click();
    }

    public static boolean hasResults(WebDriver driver) {
        List<WebElement> bookList = driver.findElements(By.cssSelector("table > tbody > tr"));
        for (WebElement tr : bookList) {
            for (WebElement td : tr.findElements(By.tagName("td"))) {
                if (td.getText().equals("T-SQL Fundamentals") || td.getText().equals("Itzik Ben-Gan") || td.getText().equals("9781509302000")) {
                    return true;
                }
            }
        }
        return false;
    }
    public static String noBooks(WebDriver driver) {
        WebElement noBooks = driver.findElement(By.cssSelector("section > div"));
        return noBooks.getText();
    }
}
