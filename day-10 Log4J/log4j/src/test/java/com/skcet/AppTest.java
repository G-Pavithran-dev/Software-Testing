package com.skcet;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    Logger logger;
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup(){
        // driver = new BraveDriver();
        // logger = LogManager.getLogger(getClass());
        // wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println("BeforeTest");
    }

    @BeforeMethod
    public void before()
    {
        System.out.println("before method");
    }

    @Test
    public void openMoneyControl() {
        System.out.println("Test");
    }

    @AfterTest
    public void close()
    {
        driver.quit();
    }
}
