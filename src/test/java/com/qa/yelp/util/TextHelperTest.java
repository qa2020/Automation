package com.qa.yelp.util;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This test class tests the TextHelper APIs. 
 *
 */
public class TextHelperTest {
	
	@Test
	public void testHelperText()
	{
		int[] totals = TextHelper.fetchTotals("Showing 1-30 of 518");
		System.out.println("Printing totals ::" + totals[0] + "," + totals[1]);
		Assert.assertEquals(totals[0], 518);
		Assert.assertEquals(totals[1], 30);
	}
}
