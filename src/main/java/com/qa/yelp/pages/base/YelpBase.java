package com.qa.yelp.pages.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.yelp.util.Constants;
import com.qa.yelp.util.CustomWebDriverEventListener;

/**
 * This class is the base class for all the pages and the test pages.
 *
 */
public class YelpBase 
{
	protected static WebDriver driver;
	protected static Properties prop;
	private static CustomWebDriverEventListener customListener;
	private  static EventFiringWebDriver efw;
	
	public YelpBase() 
	{
		FileInputStream file = null;
		try {
		file = new FileInputStream(Constants.OBJECT_REPO_FILE);
		prop = new Properties();
		prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize the core components like driver, properties, listener, browser etc.
	 */	
	public void initializer()
	{
		String Reqbrowser = prop.getProperty("browser");
		if(Reqbrowser.equals("chrome")) 
		{
			System.setProperty(Constants.CHROME_DRIVER_KEY, Constants.CHROME_DRIVER_VALUE);
			driver = new ChromeDriver();
		} else if(Reqbrowser.equals("edge")) 
		{
			//TODO: add the code for the other browsers like Edge.
		}
		efw = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		customListener = new CustomWebDriverEventListener();
		efw.register(customListener);
		driver = efw;

		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIL, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	}

}
