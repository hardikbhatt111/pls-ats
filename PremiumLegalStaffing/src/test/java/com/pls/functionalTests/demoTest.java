package com.pls.functionalTests;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pls.automation.*;
import com.pls.utilities.*;

@SuppressWarnings("unused")
public class demoTest{
	
	 
	GetPageElements gp = new GetPageElements();
	Config cfg = new Config();
	
  @BeforeClass
  public void beforeTest() {
	  System.out.println("Hello"); 
	  BaseUi.launchApplication();
  }
  @Test
  public void logina() throws Exception {
	
     gp.GetElement(ExcelUtility.getCellData(0,1,0), ExcelUtility.getCellData(0,1,1)).click();
     gp.GetElement(ExcelUtility.getCellData(0,2,0), ExcelUtility.getCellData(0,2,1)).click();
     gp.GetElement(ExcelUtility.getCellData(0,3,0), ExcelUtility.getCellData(0,3,1)).sendKeys("user@phptravels.com");
     gp.GetElement(ExcelUtility.getCellData(0,4,0), ExcelUtility.getCellData(0,4,1)).sendKeys("demouser");
     gp.GetElement(ExcelUtility.getCellData(0,5,0), ExcelUtility.getCellData(0,5,1)).click();
	  
  }
  
}
