package se.yrgo.integrations.pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;

        if (!driver.getTitle().equals("The Library")) {
            throw new IllegalStateException("wrong site");
        }
    }

}
