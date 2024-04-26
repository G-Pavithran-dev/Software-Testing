package com.skcet;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    Logger log;
    ExtentReports extentReports;
    ExtentTest test;
    XSSFWorkbook workbook;
    XSSFSheet sheet;

    static final String FILE_URL = "D:\\projects\\Software-Testing\\CC2\\cc2\\src\\main\\resources\\report.html";
    String BOOK_NAME = "";
    String eleXpath = "";


    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        actions = new Actions(driver);
        log = LogManager.getLogger(getClass());
        extentReports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(FILE_URL);
        extentReports.attachReporter(spark);
        BOOK_NAME = "Chetan Bhagat";
    }

    @Test
    public void openWebpage() {
        driver.get("https://www.barnesandnoble.com/");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("All")));
        driver.findElement(By.linkText("All")).click();

        driver.findElement(By.linkText("Books")).click();
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[2]/div[1]/input[1]")).sendKeys(BOOK_NAME);
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/span/button")).click();

        eleXpath = "//*[@id=\"searchGrid\"]/div/section[1]/section[1]/div/div[1]/div[1]/h1/span";
        WebElement element = driver.findElement(By.xpath(eleXpath));

        if(element.getText().equals(BOOK_NAME))
        {
            System.out.println("Book name presents");
        }
        else{
            System.out.println("Book name doesn't exists");
        }
    }

    @Test(dependsOnMethods = "openWebpage")
    public void addToCart() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        actions
            .moveToElement(driver.findElement(By.xpath("//*[@id=\"rhfCategoryFlyout_Audiobooks\"]")))
            .perform();
        
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]")).click();
        
        eleXpath = "//*[@id=\"addToBagForm_2940159543998\"]/input[11]";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(eleXpath)));
        driver.findElement(By.xpath(eleXpath));

        driver.findElement(By.xpath("//*[@id=\"addToBagForm_2940159543998\"]/input[11]")).click();     
    }

    @AfterTest
    public void close()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.quit();
    }
}
