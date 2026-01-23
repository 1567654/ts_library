package se.yrgo.integrations.pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class StartPage {
    private WebDriver driver;

    public StartPage(WebDriver driver) {
        this.driver = driver;

        if (!driver.getTitle().equals("The Library")) {
            throw new IllegalStateException("wrong site");
        }
    }

    public void search() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement searchBtn = driver.findElement(By.cssSelector(".max-w-md > .btn.btn-primary"));
        searchBtn.click();
    }

}
