package com.qa.yelp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.qa.yelp.pages.base.YelpBase;



public class HomePage extends YelpBase
{
	public SecondPage findResturants_1() 
	{
		WebElement find = driver.findElement(By.xpath(prop.getProperty("find")));
		find.click();
		 driver.findElement(By.xpath(prop.getProperty("find"))).sendKeys(Keys.ARROW_DOWN);
		 driver.findElement(By.xpath(prop.getProperty("find"))).sendKeys(Keys.ENTER);
		return new SecondPage();
		
	}
}
