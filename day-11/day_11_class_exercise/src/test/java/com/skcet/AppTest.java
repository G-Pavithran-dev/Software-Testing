package com.skcet;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {
    WebDriver driver;
    WebDriverWait wait;

    // Action action;
    @BeforeTest
    public void beforeTest() {
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void testing() {
        driver.get(
                "https://www.moneycontrol.com/stocks/cptmarket/compsearchnew.php?search_data=&cid=&mbsearch_str=&topsearch_type=1&search_str=Reliance+Industries");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.findElement(By.id("search_str")).sendKeys("Reliance Industries");
        driver.findElement(By.xpath("/html/body/div[3]/header/div[1]/div[1]/div/div/div[2]/div/a")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement element = driver
                .findElement(By.xpath("/html/body/div[8]/div[3]/div[2]/div/table/tbody/tr[1]/td[1]/p/a"));
        if (element.getText().equals("Reliance Industries Ltd")) {
            System.out.print("Succes");
        } else {

            System.out.print("faled");
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement hover = driver.findElement(By.linkText("Mutual Funds"));
        Actions act = new Actions(driver);
        act.moveToElement(hover).perform();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.linkText("SIP Return")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Select fund = new Select(
                driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[3]/div[2]/div[2]/div[2]/form/select")));
        fund.selectByValue("Axis Mutual Fund");

        Select scheme = new Select(
                driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[3]/div[2]/div[2]/div[4]/form/select")));
        scheme.selectByValue("Axis Arbitrage Fund - Regular Plan (D)");

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[3]/div[2]/div[2]/form/div[2]/input"))
                .sendKeys("100000");
        driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[3]/div[2]/div[2]/form/div[6]/div[2]/input"))
                .sendKeys("2021-08-02");
        driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[3]/div[2]/div[2]/form/div[6]/div[4]/input"))
                .sendKeys("2023-08-17");
        driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[3]/div[2]/div[2]/form/div[8]/input")).click();

        WebElement verify = driver
                .findElement(By.xpath("/html/body/div[9]/div[2]/div/div[3]/div[2]/div[6]/table/tbody/tr[1]/td[2]"));
        if (verify.getText().equals("August 02, 2021 to August 17, 2023")) {
            System.out.print("Succes");
        } else {

            System.out.print("faled");
        }
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

}