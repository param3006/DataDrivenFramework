package com.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.xlsUtils.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestClass1 {

	WebDriver driver;
	xlsUtils reader;

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.edge.driver",
				"C:\\Users\\7EIIN\\eclipse-workspace\\DataDrivenTest\\Drivers\\msedgedriver.exe");

		/*
		 * DesiredCapabilities caps = new DesiredCapabilities();
		 * caps.setAcceptInsecureCerts(true);
		 */

		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@DataProvider
	public Iterator<Object[]> getTestData() {
		reader = new xlsUtils(
				"C:\\Users\\7EIIN\\eclipse-workspace\\DataDrivenTest\\src\\com\\testData\\Login Data.xlsx");

		ArrayList<Object[]> testData = (ArrayList<Object[]>) reader.getAllData("Sheet1");
		return testData.iterator();
	}

	@Test(dataProvider = "getTestData")
	public void testFile(String Level1, String Level2, String Level3, String Level4)
			throws IOException, InterruptedException {

		driver.get("https://www.osram.com/am/");

		/*
		 * Actions act = new Actions(driver);
		 * act.moveToElement(driver.findElement(By.xpath("//h2[contains(text(),'" +
		 * Level3 +
		 * "')]/ancestor::div[@class='img-txt-wrapper var-column-size hover-opacity clickblock-one-link'][1]/img"
		 * )), 10, 20).click().release().build().perform();
		 */

		driver.findElement(By.xpath("//h2[contains(text(),'" + Level3 + "')]")).click();
		driver.findElement(By.xpath(
				"//div[@class='item-card-details-wrapper']/descendant::h2[@class='font-headline-and-product-name item-card-title-name' and contains(text(),'"
						+ Level4 + "')]"))
				.click();
		WebElement actL4Txt = driver
				.findElement(By.xpath("//div[@class='breadcrumb-frame active']/div/span[@class='breadcrumb-text']"));
		Assert.assertEquals(Level4, actL4Txt.getText());

		WebElement actL3Txt = driver.findElement(By.xpath(
				"//div[@class='breadcrumb-frame active']/div/span[@class='breadcrumb-text']/ancestor::a/preceding-sibling::a[1]/div/div/span[@class='breadcrumb-text']"));
		Assert.assertEquals(Level3, actL3Txt.getText());

		WebElement actL2Txt = driver.findElement(By.xpath(
				"//div[@class='breadcrumb-frame active']/div/span[@class='breadcrumb-text']/ancestor::a/preceding-sibling::a[2]/div/div/span[@class='breadcrumb-text']"));
		Assert.assertEquals(Level2, actL2Txt.getText());

		WebElement actL1Txt = driver.findElement(By.xpath(
				"//div[@class='breadcrumb-frame active']/div/span[@class='breadcrumb-text']/ancestor::a/preceding-sibling::a[3]/div/div/span[@class='breadcrumb-text']"));
		Assert.assertEquals(Level1, actL1Txt.getText());

		// span[@class='breadcrumb-text' and contains(text(),'LED high and low beam
		// lamps')]/ancestor::a/preceding-sibling::a/descendant::span[contains(text(),'Products')]

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
