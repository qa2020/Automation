package com.qa.yelp.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.qa.yelp.util.Constants;
import com.qa.yelp.util.FileUtil;
import com.qa.yelp.util.TextHelper;
import com.qa.yelp.pages.base.YelpBase;

/**
 * This is the page where the search and the filters are applied. 
 *
 */
public class SecondPage extends YelpBase
{
	
	/**
	 * This method searching again with the "Restaurants Pizza"
	 * @return
	 * @throws InterruptedException
	 */
	public void findRestaurantsPizza() throws InterruptedException
	{
		WebElement search2 = driver.findElement(By.xpath(prop.getProperty("find2")));
		search2.clear();
		search2.sendKeys(Constants.SEARCH_TEXT);
		search2.sendKeys(Keys.ENTER);
		computeTotals();
	} 
	
	/**
	 * This method applies the filter on the page.
	 * @param neignorhood
	 * @param featureName
	 * @throws InterruptedException
	 */
	public void applyFilters(String neignorhood, String featureName) throws InterruptedException 
	{
		System.out.println("in applyfilter");
		WebElement allFilters = driver.findElement(By.xpath(prop.getProperty("allFilters")));
		allFilters.click();
		System.out.println("ending applyfilter");
		WebElement n1 = driver.findElement(By.xpath(prop.getProperty(neignorhood)));
		n1.click();
		System.out.println(n1.isSelected());
		WebElement feature = driver.findElement(By.xpath(prop.getProperty(featureName)));
		feature.click();
		computeTotals();
	}
	
	/**
	 * This method is used to extract the grand total and the page total.
	 */
	private void computeTotals()
	{
		WebElement totalResult = driver.findElement(By.xpath(prop.getProperty("totalResult")));
		String result = totalResult.getText();
		System.out.println(result);
		int[] totals = TextHelper.fetchTotals(result);
		System.out.println("Grand total ::" + totals[0]);
		System.out.println("Page total ::" + totals[1]);
	}
	
	/**
	 * This method reports the stars for each restaurant.
	 * @return
	 * @throws Exception
	 */
	public int reportStars() throws Exception 
	{	
		List<WebElement> restList = driver.findElements(By.xpath(prop.getProperty("resturantNameList")));
		List<WebElement> starsList = driver.findElements(By.xpath(prop.getProperty("starsList")));
		System.out.println("Restaurants size ::" + restList.size());
		System.out.println("Stars size ::" + starsList.size());
		Object[][] data = new Object[restList.size()][2];
		
		for(int i = 0; i < restList.size(); i++)
		{
			System.out.println("Rest name::" + restList.get(i).getText() + ", star ::" + starsList.get(i).getAttribute("title"));
			for(int j = 0; j < 2; j++)
			{
				//System.out.println("Rest name::" + restList.get(i).getText() + ", star ::" + starsList.get(j).getAttribute("title"));
				if(j == 0)
					data[i][j] = restList.get(i).getText();
				else
					data[i][j] = starsList.get(i).getAttribute("title");
			}
		}
		
		FileUtil.writeData(data, Constants.RESTAURANT_STARS_SHEET, new String[] {"Restaurant Name","Stars"}, Constants.STAR_REPORT_FILE_NAME);
		return restList.size();
	}
	
	/**
	 * This method reports the details of the selected restaurant.
	 * @throws Exception 
	 */
	public void getFirstRestaurantDetails() throws Exception 
	{
		List<WebElement> allRestaurants = driver.findElements(By.xpath(prop.getProperty("allRestaurants")));
		if(!allRestaurants.isEmpty()) 
		{
			allRestaurants.get(0).click();
			WebElement name = driver.findElement(By.xpath(prop.getProperty("nameOfRestaurant")));
			WebElement firstRestStarRating = driver.findElement(By.xpath(prop.getProperty("firstRestStarRating")));
			WebElement category = driver.findElement(By.xpath(prop.getProperty("category")));
			WebElement address = driver.findElement(By.xpath(prop.getProperty("address")));
			WebElement phone = driver.findElement(By.xpath(prop.getProperty("phone")));
			
			System.out.println(name.getText()+" - "+firstRestStarRating.getAttribute("title")+" - "+category.getText()+" - "+address.getText()+" - "+phone.getText());
			Object[][] data = new Object[1][5];
			data[0][0] = name.getText();
			data[0][1] = firstRestStarRating.getAttribute("title");
			data[0][2] = category.getText();
			data[0][3] = address.getText();
			data[0][4] = phone.getText();
			FileUtil.writeData(data, Constants.RESTAURANT_DETAILS_SHEET, new String[] {"Restaurant Name","Rating", "Category", "Address", "Phone"}, Constants.FIRST_RESTDETAILS_FILE_NAME);
		}
		
	}
	

}
