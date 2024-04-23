package com.skcet;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

    @BeforeTest
    public void beforeTest() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    @Test
    public void openWebpage() throws InterruptedException {
        driver.get("https://economictimes.indiatimes.com/et-now/results");
    }
    
    @Test(dependsOnMethods = "openWebpage")
    public void openMutualFunds() throws Exception {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//nav[@id='topnav']/div[@data-ga-action='Mutual Funds']/a")));
        driver.findElement(By.xpath("//nav[@id='topnav']/div[@data-ga-action='Mutual Funds']/a")).click();
        Thread.sleep(10000);

    }

    @Test(dependsOnMethods = "openMutualFunds")
    public void selectAMC() throws Exception {
        WebElement selectEle;

        selectEle = driver.findElement(By.id("amcSelection"));
        actions.scrollToElement(selectEle).perform();
        Select select = new Select(selectEle);
        select.selectByVisibleText("Canara Robeco");
        Thread.sleep(3000);

        selectEle = driver.findElement(By.id("schemenm"));
        select = new Select(selectEle);
        select.selectByVisibleText("Canara Robeco Bluechip Equity Direct-G");
        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = "selectAMC")
    public void getDetails() throws Exception {
        driver.findElement(By.xpath("//*[@id='getDetails']")).click();
        Thread.sleep(5000);

        String current_tab = driver.getWindowHandle(); // current tab

        for (String s : driver.getWindowHandles()) {
            if (!s.equals(current_tab)) {
                // switches to newly opened tab
                driver.switchTo().window(s);
            }
        }
    }

    @Test(dependsOnMethods = "getDetails")
    public void setAmount() throws Exception {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='installment_amt']/li/span")));
        driver.findElement(By.xpath("//*[@id='installment_amt']/li/span")).click();
        driver.findElement(By.xpath("//*[@id='installment_amt']/li/ul/li[3]")).click();
    }

    @Test(dependsOnMethods = "setAmount")
    public void setPeriod() throws Exception {
        driver.findElement(By.xpath("//*[@id='installment_period']/li/span")).click();
        driver.findElement(By.xpath("//*[@id='installment_period']/li/ul/li[4]/span")).click();
    }

    @Test(dependsOnMethods = "setPeriod")
    public void navigateToReturns() throws Exception {
        driver.findElement(By.xpath("//*[@id=\"mfNav\"]/div/ul/li[2]")).click();
        WebElement details = driver
                .findElement(By.xpath("//*[@id='mfReturns']/div[2]/div[2]/ul/li[1]/table/tbody/tr[1]"));

        System.out.println(details.getText());
        Thread.sleep(10000);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

}
