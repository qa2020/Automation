package com.qa.yelp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.yelp.pages.base.YelpBase;
import com.qa.yelp.util.Constants;
import com.qa.yelp.util.FileUtil;

/**
 * This test class tests the home page related testcases
 * 
 *
 */
public class TestHomePage extends YelpBase
{
	HomePage hp;
	SecondPage sp;
	
	/**
	 * Initialize the core components like driver, properties, listener, browser etc.
	 */
	@BeforeTest
	public void setup() 
	{
		initializer();
		hp = new HomePage();
	}
	
	/**
	 * This testcase reports and validates the stars to each restaurant.
	 * @throws Exception
	 */
	@Test
	public void testReportStars() throws Exception
	{
		sp = hp.findResturants_1();
		WebElement logIn = driver.findElement(By.xpath(prop.getProperty("yelpLogo")));
		Boolean actualResult = logIn.isDisplayed();
		Assert.assertTrue(actualResult, "YelpLogo not displayed");
		
		
		int expectedCount = sp.reportStars();
		int actualCount = FileUtil.getRowCount(Constants.STAR_REPORT_FILE_NAME, Constants.RESTAURANT_STARS_SHEET);
		Assert.assertEquals(expectedCount, actualCount, "The restaurants in the excel and the page don't match");
	}
	
	/**
	 * This method closes the browser after each test method execution.
	 */
	@AfterMethod
	public void tearDown() 
	{
		driver.close();
	}
	
	
}
