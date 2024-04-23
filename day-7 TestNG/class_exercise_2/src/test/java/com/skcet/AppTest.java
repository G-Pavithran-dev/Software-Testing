package com.skcet;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    WebDriver driver;
    Actions actions;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeTest
    public void beforeTest() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testingEconomicTimes() throws Exception {

        driver.get("https://www.moneycontrol.com/");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search_str")));


        driver.findElement(By.id("search_str")).sendKeys("Reliance Industries");

        actions.moveToElement(driver.findElement(By.id("search_str"))).click();

        // Explicit wait
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("/html/body/div[3]/header/div[1]/div[1]/div/div/div[2]/div/div/form/div[2]/div[2]/ul/li[1]")));

        driver.findElement(
                By.xpath("/html/body/div[3]/header/div[1]/div[1]/div/div/div[2]/div/div/form/div[2]/div[2]/ul/li[1]"))
                .click();

        js.executeScript("window.scrollBy(0,500)");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Reliance Industries Ltd.")));
        WebElement reliance = driver.findElement(By.linkText("Reliance Industries Ltd."));

        if (reliance.isDisplayed()) {
            System.out.println("Reliance Industries is present in the page");
        } else {
            System.out.println("Reliance Industries is not present in the page");
        }

        driver.findElement(By.linkText("Mutual Funds")).click();
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0,500)");

        driver.findElement(By.xpath(
                "/html/body/section/section[1]/div/div/div[1]/div[5]/div/div/div/div[1]/div/div[3]/div[1]/div[1]/ul/li[2]/a"))
                .click();

        Thread.sleep(1000);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
