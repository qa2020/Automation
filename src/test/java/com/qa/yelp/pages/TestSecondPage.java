package com.qa.yelp.pages;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.yelp.pages.base.YelpBase;
import com.qa.yelp.util.FileUtil;

/**
 * This test class has the test cases related to the filters applied on the page.
 *
 */
public class TestSecondPage extends YelpBase
{
	private HomePage hp;
	private SecondPage sp;
	private static final String SHEETNAME = "filters";

	/**
	 * Initialize the core components like driver, properties, listener, browser etc.
	 */
	@BeforeMethod
	public void setUp() 
	{
		initializer();
		hp = new HomePage();
		sp = hp.findResturants_1();
		sp = new SecondPage();
	}
	
	/**
	 * This method reads the filter parameters from the test data sheet.
	 * @return
	 * @throws Exception
	 */
	@DataProvider
	public Object[][] getFilters() throws Exception 
	{
		Object[][] testData = FileUtil.getTestData(SHEETNAME);
		return testData;
	}
	
	/**
	 * This test case launches multiple times based on the number of filters in the test data sheet.
	 * @param neighborhood
	 * @param feature
	 * @throws InterruptedException
	 */
	@Test(priority=1,dataProvider="getFilters",alwaysRun = true)
	public void testFindRestaurantsPizza_1(String neighborhood, String feature) throws InterruptedException
	{
		sp.findRestaurantsPizza();
		sp.applyFilters(neighborhood, feature);
	}
	
	
	/**
	 * This testcase reports the details of the first selected restaurant.
	 * @throws Exception 
	 */
	@Test
	public void testGetFirstRestaurantDetails() throws Exception
	{
		sp.getFirstRestaurantDetails();
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
