package com.pls.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SuppressWarnings("unused")
public class ExcelUtility {
	
	public static XSSFSheet ExcelWSheet;
	 
	public static XSSFWorkbook ExcelWBook;

	public static XSSFCell Cell;

	public static  int startRow;

	public static   int startCol;
	
	static Config cfg = new Config();
	
	public static String FilePath = cfg.readProperty("candidateCreationFile");
	
	
	//to get the name of the caller class
	
	public static String getCallerClassName() {
		
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        String classname = null ;
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(ExcelUtility.class.getName()) && ste.getClassName().indexOf("java.lang.Thread")!=0) {
            	System.out.println("This is class name:"+ste.getClassName());
            	String classstring = ste.getClassName().toString();
            	String[] parts = classstring.split("\\.");
            	classname = parts[parts.length - 1];
            	System.out.println(parts.length);
            	System.out.println(classname);
                return classname;
            }
        }
        return classname;
     }
	
//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
	public static XSSFSheet initiateExcel(int sheetNo) throws IOException
	{
		String sheetname = getCallerClassName();
		FileInputStream ExcelFile = new FileInputStream(FilePath);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		//return ExcelWSheet = ExcelWBook.getSheetAt(sheetNo);
		return ExcelWSheet = ExcelWBook.getSheet(sheetname);
	}

	@SuppressWarnings({ "unused", "static-access" })
	public static String getCellData(int sheetNo, int RowNum, int ColNum) throws Exception {
		ExcelUtility e = new ExcelUtility();
		ExcelWSheet = e.initiateExcel(sheetNo);
		 
		try{

			Cell cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			int dataType = cell.getCellType();
			
			if (cell == null)
	            return "";

			switch (dataType)
          {
          case  1:
          return cell.getStringCellValue();               

          case  4:
          return String.valueOf(cell.getBooleanCellValue());          

          case  3:
          return "";      

          case  0:
          return String.valueOf(cell.getNumericCellValue());          

          default:
          return "Cell not found";        

          }
			
		}catch (Exception e1){

			System.out.println(e1.getMessage());

			throw (e1);

			}

		}

	
	public static void writeTestResult(int rowNo, int colNo, String Result, String FilePath) throws Exception
	{
	    
	    FileOutputStream fileOut = new FileOutputStream(FilePath);
	    	
		Row row = ExcelWSheet.getRow(rowNo);
		if(row == null){
		    row = ExcelWSheet.createRow(rowNo);
		}
		
		Cell cell = row.getCell(colNo);
		
		if(cell == null){
		cell = row.createCell(colNo);
		}
		
		cell.setCellValue(Result);
		ExcelWBook.write(fileOut);  
		fileOut.close();
		 
	        
		}
	
	}
