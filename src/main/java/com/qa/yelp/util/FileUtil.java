package com.qa.yelp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.yelp.pages.base.YelpBase;

/**
 * This class has APIs to read and write the excel files. 
 *
 */
public class FileUtil extends YelpBase
{
	/**
	 * This method reads the data from the input test data file.
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getTestData(String sheetName) throws Exception
	{
		FileInputStream file = new FileInputStream(Constants.TESTDATA_SHEET_PATH);
		Workbook book = WorkbookFactory.create(file);
		Sheet sheet = book.getSheet(sheetName);
		
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) 
		{
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) 
			{
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}
	
	/**
	 * This method returns the number of rows in the given file and the sheet.
	 * @param filePath
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
	public static int getRowCount(String filePath, String sheetName) throws Exception
	{
		FileInputStream file = new FileInputStream(filePath);
		Workbook book = WorkbookFactory.create(file);
		Sheet sheet = book.getSheet(sheetName);
		
		return sheet.getLastRowNum();
	}
	
	/**
	 * This method takes a screeshot on evenst like Webdriver errors.
	 * @throws IOException
	 */
	public static void takeScreenshotAtEndOfTest() throws IOException 
	{
		File src= ((TakesScreenshot)driver). getScreenshotAs(OutputType. FILE);
		FileUtils. copyFile(src, new File(Constants.SCREENSHOT_ON_EXCEPTION));
	}
	
	/**
	 * This method write the reports with the given contents.
	 * @param data
	 * @param sheetName
	 * @param headerArr
	 * @param outputFileName
	 * @throws Exception
	 */
	public static void writeData(Object[][] data, String sheetName, String[] headerArr, String outputFileName) throws Exception
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
        int rowNum = 0;
        System.out.println("Creating excel");

        Row headerRow = sheet.createRow(rowNum++);
        int headerColNum = 0;
        for(String header:headerArr)
        {
    		Cell cell = headerRow.createCell(headerColNum++);
    		cell.setCellValue(header);
        }
        for(int i = 0; i < data.length; i++)
        {
        	Row row = sheet.createRow(rowNum++);
        	int colNum = 0;
        	for(int j = 0; j < data[0].length; j++)
            {
        		Cell cell = row.createCell(colNum++);
        		cell.setCellValue((String)data[i][j]);
            }
        }

        FileOutputStream outputStream = new FileOutputStream(outputFileName);
        workbook.write(outputStream);
        outputStream.close();
        System.out.println("Finished writing file");
	}
	
}
	
	
	

	
	
