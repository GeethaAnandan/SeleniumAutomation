package com.demo.seleniumTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingDataFromExcelFile {
	
	
	public String[][] readDatafromXLSFile(String fileName,int sheetIndex) throws IOException
	{
		
		
		File f = new File(fileName);
		FileInputStream fin = new FileInputStream(f);
		int index =fileName.indexOf(".");
		String fileformat=fileName.substring(index);
		String valuArray[][]=null;
		if(fileformat.equals(".xls"))
		{
			
			HSSFWorkbook workbook = new HSSFWorkbook(fin);
			HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
			int rows = sheet.getLastRowNum()+1;
			System.out.println("rows count :"+rows);
			int columns = sheet.getRow(0).getLastCellNum();
			System.out.println("column count"+columns);
		   
			//Getting only string values
			/*for(int i=0;i<rows;i++)
			{
				HSSFRow row = sheet.getRow(i);
				for(int j=0;j<columns;j++)
				{
					String cell = row.getCell(j).getStringCellValue();
					System.out.print(row.getCell(j).getStringCellValue()+" ");
				}
				System.out.println();
			}*/
			
		
		   valuArray=new String[rows][columns];
			for(int i=0;i<rows;i++)
			{
				HSSFRow row = sheet.getRow(i);
				for(int j=0;j<columns;j++)
				{
					HSSFCell cell = row.getCell(j,row.CREATE_NULL_AS_BLANK);
					switch(cell.getCellType())
					{
					case HSSFCell.CELL_TYPE_BLANK:
						valuArray[i][j]="";
						break;
						
					case HSSFCell.CELL_TYPE_STRING:
						valuArray[i][j]=cell.getStringCellValue();
						break;
							
					case HSSFCell.CELL_TYPE_NUMERIC:
						valuArray[i][j]=String.valueOf(cell.getNumericCellValue());
						break;
					}
					System.out.println(valuArray[i][j]);
					
				}
			 }
			
			}
		
		
		
		else if(fileformat.equals(".xlsx"))
		{
			XSSFWorkbook book = new XSSFWorkbook(fin);
			XSSFSheet sheet = book.getSheetAt(sheetIndex);
			int rows = sheet.getLastRowNum()+1;
			int column = sheet.getRow(0).getLastCellNum();
			for (int i=0;i<rows;i++)
			{
				XSSFRow row = sheet.getRow(i);
				for(int j=0;j<column;j++) {
					XSSFCell cell = row.getCell(j,row.CREATE_NULL_AS_BLANK);
					switch(cell.getCellType())
					{
					case XSSFCell.CELL_TYPE_BLANK:
						valuArray[i][j]="";
						break;
						
					case XSSFCell.CELL_TYPE_STRING:
						valuArray[i][j]=cell.getStringCellValue();
						break;
						
					case XSSFCell.CELL_TYPE_NUMERIC:
						valuArray[i][j]=String.valueOf(cell.getNumericCellValue());
						break;
					}
				}
			}
		}
		
		return valuArray;
		
		
	}
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ReadingDataFromExcelFile obj = new ReadingDataFromExcelFile();
		String fileName="D:\\Book1.xlsx";
		
		obj.readDatafromXLSFile(fileName, 0);

	}

}
