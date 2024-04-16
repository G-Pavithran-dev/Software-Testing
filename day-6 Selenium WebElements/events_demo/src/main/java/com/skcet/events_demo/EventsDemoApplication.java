package com.skcet.events_demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventsDemoApplication {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		Actions actions = new Actions(driver);

		driver.get("https://mouse-demo-ui.vercel.app");
		Thread.sleep(5000);

		driver.manage().window().maximize();

		// double click
		WebElement double_click = driver.findElement(By.id("double_click"));
		actions
			.scrollToElement(double_click)
			.doubleClick(double_click)
			.perform();
		Thread.sleep(2000);

		driver.switchTo().alert().accept();
		Thread.sleep(5000);

		// click and relese
		WebElement click = driver.findElement(By.id("click"));
		actions
			.click(click)
			.perform();
		Thread.sleep(2000);

		driver.switchTo().alert().accept();
		Thread.sleep(4000);

		// click and hold
		WebElement hold_element = driver.findElement(By.id("click_hold"));
		actions
			.clickAndHold(hold_element)
			.pause(Duration.ofSeconds(3))
			.release()
			.perform();
		Thread.sleep(4000);

		// hover
		WebElement hover = driver.findElement(By.id("hover"));
		actions
			.moveToElement(hover)
			.perform();
		Thread.sleep(4000);

		// context_menu
		actions
			.contextClick()
			.perform();
		Thread.sleep(5000);

		actions
			.keyDown(Keys.ESCAPE)
			.keyUp(Keys.ESCAPE)
			.perform();

		// drag and drop
		driver.navigate().to("https://www.selenium.dev/selenium/web/mouse_interaction.html");
		Thread.sleep(5000);

		WebElement drag = driver.findElement(By.id("draggable"));
		WebElement drop = driver.findElement(By.id("droppable"));
		actions
			.dragAndDrop(drag, drop)
			.perform();
		Thread.sleep(15000);

		driver.quit();
	}

}
