package com.qa.yelp.util;

import org.testng.annotations.Test;

/**
 * This test class is for testing the File related APIs of FileUtil. 
 *
 */
public class FileUtilTest {
  
  @Test
  public void testWriteData() throws Exception {
	  Object[][] data = new Object[2][2];
	  data[0][0] = "Bombay Garden";
	  data[0][1] = "Five stars";
	  data[1][0] = "Italian Garden";
	  data[1][1] = "Four stars";
	  String[] headers = new String[] {"Restaurant Name","Stars"};
	  FileUtil.writeData(data, "5 stars", headers, Constants.STAR_REPORT_FILE_NAME);
  }
}
