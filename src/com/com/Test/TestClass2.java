package com.com.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestClass2 {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.edge.driver",
				"C:\\Users\\7EIIN\\git\\DataDrivenFramework\\Drivers\\msedgedriver.exe");

		/*
		 * DesiredCapabilities caps = new DesiredCapabilities();
		 * caps.setAcceptInsecureCerts(true);
		 */

		driver = new EdgeDriver();
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.worldometers.info/world-population/");
	}

	@Test(priority = 0,enabled = false)
	public void countPopulation() throws InterruptedException {
		// TODO Auto-generated method stub
		int count = 1;
		while (true) {
			String worldPopulation = driver.findElement(By.xpath("//span[@rel='current_population']")).getText();
			System.out.println(worldPopulation);
			count += 1;
			Thread.sleep(1000);
			if (count == 20) {
				System.out.println("breaking loop at 20th second");
				break;
			}
		}
	}

	@Test(priority = 1)
	public void counttodAndYearPop() throws InterruptedException {
		int count = 0;
		int timer = 1;
		while (true) {
			System.out.println("Current second" + timer);
			List<WebElement> worldAndTodPop = (List<WebElement>) driver.findElements(
					By.xpath("//div[text()='Today' or text()='This year']//parent::div//span[@class='rts-counter']"));
			System.out.println("Today");
			for (WebElement e : worldAndTodPop) {
				System.out.println(e.getText());
				count++;
				if (count == 3) {
					System.out.println("World Population");
				}

			}
			timer += 1;
			Thread.sleep(1000);
			if (timer == 21) {
				break;
			}
			count = 0;
		}

	}
}
