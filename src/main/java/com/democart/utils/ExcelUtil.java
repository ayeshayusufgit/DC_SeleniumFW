package com.democart.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	static String TEST_DATA_FILE_PATH = "./src/test/resources/testdata/testData.xlsx";
	static Workbook wb;
	static Sheet sheet;

	public static String[][] getTestData(String sheetName) {
		String[][] excelData = null;
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(TEST_DATA_FILE_PATH);
			wb = WorkbookFactory.create(fis);
			sheet = wb.getSheet(sheetName);
			excelData = new String[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {

				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					excelData[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excelData;
	}

}
