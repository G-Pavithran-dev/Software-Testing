package com.skcet.classexercise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClassExerciseApplication {

	public static void main(String[] args) {
		
		try{
			WebDriver driver = new ChromeDriver();
			driver.get("https://www.demoblaze.com");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Laptops")).click();
			Thread.sleep(5000);
			driver.findElement(By.linkText("MacBook air")).click();
			Thread.sleep(5000);
			driver.findElement(By.linkText("Add to cart")).click();
			// driver.findElement(By.linkText("Ok")).click();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			Thread.sleep(3000);
			driver.findElement(By.linkText("Cart")).click();
			Thread.sleep(5000);
			List<WebElement> list =  driver.findElements(By.tagName("td"));

			System.out.println(list.);
			int i = 0;
			for(WebElement ele : list)
			{
				if(i == 1 || i ==2)
					System.out.println(ele.getText());

				i++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
