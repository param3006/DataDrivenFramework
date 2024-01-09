package com.Test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class corouselHandling {
	WebDriver driver;

	@Test
	public void testCorousel() throws InterruptedException {

		getCorouselValues("Bestselling toys");
	}

	@BeforeMethod
	public void setUp() {

		


		driver = new EdgeDriver();
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.noon.com/uae-en/");
	}

	private List<String> getCorouselValues(String sectionName) throws InterruptedException {
		List<String> titleCollectionList = new ArrayList<>();
		boolean elementFound = false;
		while (!elementFound) {
			try {
				WebElement sectionElement = driver.findElement(By.xpath("//h2[text()='" + sectionName + "']"));
				scrollToElement(sectionElement);
				if (sectionElement.isDisplayed() == true) {
					break;
				}
			} catch (Exception e) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,200)", "");
			}
		}

		WebElement corouselBtn = driver.findElement(By.xpath("//h2[text()='" + sectionName
				+ "']/parent::div/parent::div/following-sibling::div/descendant::div[starts-with(@class,'swiper-button-next custom-navigation')]")); // fetch
																																						// nextCorousel
																																						// button
																																						// according
																																						// to
																																						// sectionName

		while (corouselBtn.isDisplayed() == true) {
			corouselBtn.click();
		}
		
		String corouselTxt = "//*[.='" + sectionName
				+ "']//parent::div//following-sibling::div//descendant::div[@class='sc-51b852f7-19 cwDrLD']/child::div[@class='sc-51b852f7-21 bCWDQF']";
		List<WebElement> listOfElements = driver.findElements(By.xpath(corouselTxt));
		for (WebElement e : listOfElements) {
			String titleValue = e.getAttribute("title");
			titleCollectionList.add(titleValue);
		}
		Collections.sort(titleCollectionList, String.CASE_INSENSITIVE_ORDER);

		for (String text : titleCollectionList) {
			System.out.println(text);
		}

		return titleCollectionList;
	}

	private void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
