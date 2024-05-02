package com.skcet;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    WebDriver driver;
    Actions actions;
    TakesScreenshot screenshot;
    XSSFWorkbook workbook;
    WebDriverWait wait;
    Logger log = LogManager.getLogger(getClass());

    private final String SITE_URL = "https://www.opentable.com";
    private String data = "";

    @BeforeTest
    public void initialSetup() {
        this.driver = new ChromeDriver();
        this.actions = new Actions(driver);
        this.screenshot = (TakesScreenshot) driver;
        try{
            this.workbook = new XSSFWorkbook("src\\main\\resources\\data.xlsx");
            this.data = workbook.getSheetAt(0).getRow(1).getCell(0).getStringCellValue();
        }
        catch(Exception e){
            log.error("Test stooped due to " + e.getMessage());
        }
        log.info("Test initated");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void testOPenTable() throws InterruptedException{

        log.info("Navigating to webpage");
        driver.get(SITE_URL);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"home-page-autocomplete-input\"]")));
        log.info("Search for Bangalore data from excel");
        driver.findElement(By.xpath("//*[@id=\"home-page-autocomplete-input\"]")).sendKeys(data);

        log.info("click \"let's go\" button");
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/header/div/span/div/div/div[2]/div[2]/button")).click();

        log.info("scrolling down to view filters");
        actions
            .scrollToElement(driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/section/div[8]")))
            .perform();

        log.info("choosing asian as a cusine filter");
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/section/div[6]/div/label[4]/span[2]")).click();

        log.info("Selecting the first asian restarunt");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mainContent\"]/div/div/div[2]/div/div[2]/div[1]/div[1]/a")));
        driver.navigate().to("https://www.opentable.com/r/far-and-east-four-seasons-hotel-bengaluru?corrid=67ff3f54-748d-438b-a371-80882b87998e&avt=eyJ2IjoyLCJtIjoxLCJwIjowLCJzIjowLCJuIjowfQ&p=2&sd=2024-05-02T19%3A00%3A00");

        log.info("switch to new tab");
        String curwindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for(String window : windows)
        {
            if(!window.equals(curwindow))
            {
                driver.switchTo().window(window);
            }
        }

        File src = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File("./screenshots/screenshot.png"));
            log.info("Screenshot Taken");
        } catch (IOException e) {
            log.error("test failed due to "+ e.getMessage());
            e.printStackTrace();
        }

        // Select select = new Select(driver.findElement(By.xpath("//*[@id=\"restProfileSideBarDtpPartySizePicker\"]")));
        // select.selectByIndex(3);

        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[2]/div[1]/section[2]/div[5]/article[1]/div/article/ul/li[1]/a")).click();
    }

    @AfterTest
    public void quitTest(){
        driver.quit();
    }
}
