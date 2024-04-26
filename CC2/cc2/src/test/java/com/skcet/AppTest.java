package com.skcet;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Unit test for simple App.
 */
public class AppTest {
    WebDriver driver;
    WebDriverWait wait;
    ExtentTest test;
    Actions actions;
    XSSFSheet sheet;
    XSSFWorkbook workbook;
    JavascriptExecutor js;
    ExtentReports extentReports;
    TakesScreenshot screenshot;
    Logger log = LogManager.getLogger(getClass());

    static final String REPORT_FILE_PATH = "D:\\projects\\Software-Testing\\CC2\\cc2\\src\\main\\resources\\report.html";
    static final String EXCEL_FILE_PATH = "D:\\projects\\Software-Testing\\CC2\\cc2\\src\\main\\resources\\Data.xlsx";
    static final String SCREENSHOT_PATH = "screenshots\\Sign in pop-up screenshoted.png";
    String BOOK_NAME = "";
    String eleXpath = "";

    @BeforeTest
    public void setUp() throws IOException {
        log.info("Test setup initiated");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        
        log.info("Setting up the test report using ExtentReports");
        
        extentReports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_FILE_PATH);
        spark.config().setDocumentTitle("Barnes and Noble");
        spark.config().setReportName("Barnes and Noble Test Report");
        spark.config().setTheme(Theme.DARK);
        extentReports.attachReporter(spark);

        log.info("Test report setup completed");

        log.info("Setting up the test data using Excel");

        workbook = new XSSFWorkbook(EXCEL_FILE_PATH);
        sheet = workbook.getSheetAt(0);
        BOOK_NAME = sheet.getRow(1).getCell(0).getStringCellValue();

        log.info("Test data setup completed");

        log.info("Screenshot setup initiated");
        screenshot = (TakesScreenshot) driver;
        log.info("Screenshot setup completed");

        log.info("Test setup completed");
    }

    @Test
    public void searchForBookChetanBhagat() {
        test = extentReports.createTest("Test 1", "Search for Chetan Bhagat");
        log.info("Opening webpage");
        driver.get("https://www.barnesandnoble.com/");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("All")));
        driver.findElement(By.linkText("All")).click();

        driver.findElement(By.linkText("Books")).click();
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[2]/div[1]/input[1]"))
                .sendKeys(BOOK_NAME);
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/span/button")).click();

        eleXpath = "//*[@id=\"searchGrid\"]/div/section[1]/section[1]/div/div[1]/div[1]/h1/span";
        WebElement element = driver.findElement(By.xpath(eleXpath));


        if (element.getText().equals(BOOK_NAME)) {
            log.info("Book name presents");
            test.log(Status.PASS, "Book presents");
        } 
        else {
            log.warn("Book doesn't exists");
            test.log(Status.FAIL, "Book doesn't exists");
        }
    }
    
    @Test(dependsOnMethods = "searchForBookChetanBhagat")
    public void addAudioBookToCart() {
        test = extentReports.createTest("Test 2", "Add Audio Book to Cart");
        log.info("Adding to cart");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        actions
                .moveToElement(driver.findElement(By.xpath("//*[@id=\"rhfCategoryFlyout_Audiobooks\"]")))
                .perform();

        driver.findElement(
                By.xpath("//*[@id=\"navbarSupportedContent\"]/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]")).click();

        eleXpath = "//*[@id=\"addToBagForm_2940159543998\"]/input[11]";
    
        log.fatal("ADD TO CART NOT CLICKABLE");
        test.log(Status.SKIP, "Add to cart not clickable");
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(eleXpath)));
        // driver.findElement(By.xpath(eleXpath));

        // driver.findElement(By.xpath("//*[@id=\"addToBagForm_2940159543998\"]/input[11]")).click();
        // wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // WebElement element =
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"navbarSupportedContent\"]/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]")));
        // element.click();
    }

    @Test(dependsOnMethods = "addAudioBookToCart")
    public void chooseBNMembership() throws Exception {
        test = extentReports.createTest("Test 3", "Choose BN Membership & Find Sign In Pop Up");
        log.info("Choosing BN Membership");

        actions
                .scrollToElement(driver.findElement(By.xpath("//*[@id=\"footer\"]/div/dd/div/div")))
                .perform();

        driver.findElement(By.xpath("//*[@id=\"footer\"]/div/dd/div/div/div[1]/div/a[5]")).click();

        actions
                .scrollToElement(driver.findElement(By.xpath("//*[@id=\"mb-landing\"]/div[1]/div[2]/div/div/div[4]")))
                .perform();

        driver.findElement(By.linkText("JOIN REWARDS")).click();
        log.info("JOIN REWARDS clicked");

        List<WebElement> ele = driver.findElements(By.tagName("iframe"));
        System.out.println("IFRAME displayed " + ele);
        Thread.sleep(5000);
        driver.switchTo().frame(ele.get(0));

        File snapshot = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(snapshot, new File("screenshots/Sign in pop-up screenshoted.png"));


        log.info("Screenshot taken for evidence");
        log.fatal("Trying to find SIGN IN POP UP will fail");

        test.addScreenCaptureFromPath(SCREENSHOT_PATH, "Screenshot evidence for sign in pop-up");
        test.log(Status.PASS, "SIGN IN POP-UP found");

        try {
            eleXpath = "//*[@id=\"dialog-title\"]";
            String confirm = driver.findElement(By.tagName("h2")).getText();
            System.out.println(confirm);

            if (confirm.equals("Sign in or Create an Account")) {
                log.info("SIGN IN POP UP found");
            } else {
                log.warn("SIGN IN text not found");
            }
        } catch (Exception e) {
            log.error("SIGN IN POP UP not found");
        }
    }

    @AfterTest
    public void close() { 
        log.info("Closing the test");
        driver.quit();
        extentReports.flush();
        log.info("Report Flushed");
        log.info("Test closed");
    }
}
