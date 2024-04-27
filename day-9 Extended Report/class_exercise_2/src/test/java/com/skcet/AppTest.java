package com.skcet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
    public ExtentReports reports;
    public XSSFWorkbook workbook;
    public WebDriver driver;
    public String url;

    @BeforeTest
    public void before() throws IOException {

        // getting the url from the Excel sheet
        String xlpath = "D:\\programming\\vs code programs\\vs code\\Software Testing\\Practice\\gameblaze\\src\\Excel\\urplath.xlsx";

        FileInputStream file = new FileInputStream(xlpath);

        workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        url = sheet.getRow(1).getCell(0).getStringCellValue();
        url = url.replaceAll("\"", "");
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // workbook.close();

        String res = "D:\\programming\\vs code programs\\vs code\\Software Testing\\Practice\\gameblaze\\src\\report\\result.html";
        reports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(res);
        reports.attachReporter(spark);

        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Grow");

        driver = new ChromeDriver();
    }

    @Test
    public void shouldAnswerWithTrue() throws IOException {

        driver.get(url);

        driver.manage().window().maximize();

        ExtentTest test = reports.createTest("test 1");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement cal = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//*[@id=\'footer\']/div/div[1]/div/div[1]/div[2]/div[3]/a[2]")));
        cal.click();

        WebElement top = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'root\']/div[2]/h1")));

        String title = top.getText();
        if (title.equals("Calculators")) {

            System.out.println("The calc is there");
            test.log(Status.PASS, "The calc is there");

        } else {
            test.log(Status.FAIL, "The calc is not there");
        }

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File("screenshot.png"));
        WebElement homeLoan = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'root\']/div[2]/div[2]/a[15]/div/p[1]")));
        homeLoan.click();

        XSSFSheet sheet = workbook.getSheetAt(1);

        String loan = sheet.getRow(1).getCell(0).getNumericCellValue() + "";
        String rate = sheet.getRow(1).getCell(1).getNumericCellValue() + "";
        String year = sheet.getRow(1).getCell(2).getNumericCellValue() + "";

        WebElement loanAmt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'LOAN_AMOUNT\']")));
        loanAmt.sendKeys(loan);

        driver.findElement(By.xpath("//*[@id=\'RATE_OF_INTEREST\']")).sendKeys(rate);
        driver.findElement(By.xpath("//*[@id=\'LOAN_TENURE\']")).sendKeys(year);
        String amtDetails = driver
                .findElement(By.xpath("//*[@id=\'root\']/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div/p")).getText();

        ExtentTest test2 = reports.createTest("test 2");

        if (amtDetails.equals("Your Amortization Details (Yearly/Monthly)")) {

            System.out.println("The string is found");
            test2.log(Status.PASS, "The string is found");
        } else {
            test2.log(Status.FAIL, "The string not found");
        }

    }

    @AfterTest
    public void After() throws IOException {

        workbook.close();

        reports.flush();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.close();

    }
}
