package com.skcet;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class POMexample {
    public WebDriver driver;
    POMexampleElements poMexampleElements;

    @Test
    public void test1() throws InterruptedException {
        String expectedTitle = "SKCET - Google Search";
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        Thread.sleep(3000);


        poMexampleElements = new POMexampleElements(driver); //object for element class
        poMexampleElements.searchContent("SKCET");  // calling searchContent method from element class
        poMexampleElements.clickSubmit(); // calling clickSubmit method from element class
        Thread.sleep(3000);
        poMexampleElements.AssertGivenTitle(expectedTitle);
        
    }
}
