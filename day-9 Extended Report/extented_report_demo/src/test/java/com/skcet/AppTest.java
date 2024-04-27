package com.skcet;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
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
    WebDriverWait wait;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    Row row;
    
    @BeforeTest
    public void preTest() throws IOException{
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // workbook = new XSSFWorkbook();
        // workbook.createSheet("Login Credentials");
        
        // sheet = workbook.getSheet("login Credentials");

        // row = sheet.createRow(0);
        // row.createCell(0).setCellValue("Username");
        // row.createCell(1).setCellValue("Password");

        // row = sheet.createRow(1);
        // row.createCell(0).setCellValue("pavi@gmail.com");
        // row.createCell(1).setCellValue("pavi1234");

        // try {
        //     FileOutputStream outputStream = new FileOutputStream("LoginCredentials.xlsx");
        //     workbook.write(outputStream);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        FileInputStream input = new FileInputStream("LoginCredentials.xlsx");
        workbook = new XSSFWorkbook(input);
    
        sheet = workbook.getSheetAt(0);
        workbook.close();
    }
    
    @Test(dataProvider = "data")
    public void testApp(String Username, String Password) throws Exception
    {
        System.out.println(Username+ " " + Password);
        driver.get("https://www.google.com");   
    }

    @DataProvider(name = "data")
    public Object[][] fetchData()
    {
        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];

        for(int i = 0;i < rows;i++)
        {
            row = sheet.getRow(i+1);
            for(int j = 0;j < cols;j++)
            {
                data[i][j] = row.getCell(j).getStringCellValue();
            }
        }

        return data;
    }
}
