package com.skcet;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {

    @Test(dataProvider = "data")
    public void test1(String username, String password) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//*[@name='username']")).sendKeys(username);
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
    }

    @DataProvider(name = "data")
    public Object[][] fetchData() throws IOException {
        FileInputStream fs = new FileInputStream("D:\\input.xlsx");
        
        // create an workbook
        XSSFWorkbook workbook = new XSSFWorkbook(fs);

        // to get the first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);

        // to find total number of rows
        int rowCount = sheet.getLastRowNum(); 

        // to find total number of columns
        int colCount = sheet.getRow(0).getLastCellNum(); 
        System.out.println("Row count: " + rowCount);
        System.out.println("Column count: " + colCount);
        // to store any type of data
        Object[][] data = new Object[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1); 
            for (int j = 0; j < colCount; j++) {
                data[i][j] = row.getCell(j).getStringCellValue();
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        workbook.close();
        fs.close();

        return data;
    }

}
