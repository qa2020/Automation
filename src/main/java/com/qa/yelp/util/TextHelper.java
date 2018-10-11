package com.qa.yelp.util;

/**
 * This class helps to handle the String operations like fetching the grand total and page total from the given text. 
 *
 */
public class TextHelper {

	/**
	 * This method fetches the grand total and page total from the given text.
	 * @param totalsText
	 * @return
	 */
	public static int[] fetchTotals(String totalsText)
	{
		int[] totals = new int[2];
		String[] textSplit = totalsText.split("of");
		int grandTotal = Integer.valueOf(textSplit[1].trim());
		int currentPageTotal = Integer.valueOf(textSplit[0].substring(textSplit[0].indexOf("-")+1).trim());
		totals[0] = grandTotal;
		totals[1] = currentPageTotal;
		return totals;
	}
}
