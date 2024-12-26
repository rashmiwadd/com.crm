package com.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String getDataFromExcel(String SheetName, int rowNum, int cellNum) throws EncryptedDocumentException, IOException{


		FileInputStream fis=new FileInputStream("./testData/TestScripdata.xlsx"); 
		Workbook wb=WorkbookFactory.create(fis);
		String data = wb.getSheet(SheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
        wb.close();
		return data;
	}
	public int getRowCount(String SheetName) throws IOException {
		
		FileInputStream fis=new FileInputStream("./testData/TestScripdata.xlsx"); 
		Workbook wb=WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(SheetName).getLastRowNum();
        wb.close();
		return rowCount;
		
	}
	
	public void setDataInExcel(String SheetName, int rowNum, int cellNum,String data) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream("./testData/TestScripdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(SheetName).getRow(rowNum).createCell(cellNum).setCellValue(data);
		
		FileOutputStream fos= new FileOutputStream("./testData/TestScripdata.xlsx");
		wb.write(fos);
		wb.close();
		
	}

}
