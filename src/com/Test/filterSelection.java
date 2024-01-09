package com.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class filterSelection {

	public WebDriver driver;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.edge.driver",
				"C:\\Users\\7EIIN\\git\\DataDrivenFramework\\Drivers\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get("https://www.t-mobile.com/cell-phones");
		driver.manage().window().maximize();

		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void selectFilter() throws InterruptedException {
		selectFilter("Deals", "all");
		
		selectFilter("Brands", "Apple","Nokia");
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		driver.quit();
	}

	private void selectFilter(String filterType, String... filterValues) throws InterruptedException {

		Actions actions = new Actions(driver);

		String xpathForFilterType;
		WebDriverWait wait = new WebDriverWait(driver, 20);

		try {
			xpathForFilterType = "//legend[normalize-space()='" + filterType + "' and @class = 'fx-flex']";
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathForFilterType)));
			actions.moveToElement(driver.findElement(By.xpath(xpathForFilterType))).click().release().build().perform();
			for (String filterValue : filterValues) {

				if (filterValue.equalsIgnoreCase("all")) {
					List<WebElement> listOfFilters = driver
							.findElements(By.xpath("//span[@class='filter-display-name']"));
					for (WebElement filter : listOfFilters) {
						filter.click();
					}

				} else {
					String xpathForValues = "//span[contains(text(),'" + filterValue
							+ "') and @class='filter-display-name']";

					driver.findElement(By.xpath(xpathForValues)).click();
				}

			}
		} catch (Exception e) {
			Thread.sleep(20000);
//			JavascriptExecutor js=(JavascriptExecutor) driver;
//			js.executeScript("window.scrollBy(0,100)","");
		}
//		JavascriptExecutor js=(JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath(xpathForFilterType)));

	}

}
