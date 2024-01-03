package com.Test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;

public class svgElementHandling {
	WebDriver driver;

	@Test
	public void doAction() throws InterruptedException {
		clickCity("ohio");
	}

	private void clickCity(String cityName) throws InterruptedException {
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='capc-map-embed mode-widget']//child::iframe[starts-with(@id,'map-instance')]")));
//		driver.switchTo().frame("map-instance-19220");
		String xPathForCities = "//*[name()='svg']//*[local-name()='g' and @class='region' and @id='"+cityName.toLowerCase()+"']";
		
		driver.findElement(By.xpath(xPathForCities)).click();
		Thread.sleep(3000);
		
	}
	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.edge.driver",
				"C:\\Users\\7EIIN\\git\\DataDrivenFramework\\Drivers\\msedgedriver.exe");

		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);	
		driver.get("https://petdiseasealerts.org/forecast-map/");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
