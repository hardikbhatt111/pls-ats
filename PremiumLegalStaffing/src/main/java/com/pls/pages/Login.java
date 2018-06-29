package com.pls.pages;

import com.pls.automation.GetPageElements;
import com.pls.utilities.Config;
import com.pls.utilities.ExcelUtility;


public class Login {
	GetPageElements gp = new GetPageElements();
	Config cfg = new Config();
	 public void UserLogin() throws InterruptedException, Exception {
		 gp.GetElement(ExcelUtility.getCellData(0,1,0), ExcelUtility.getCellData(0,1,1)).sendKeys("project.ats@premierlegalstaffing.com");
			gp.GetElement(ExcelUtility.getCellData(0,2,0), ExcelUtility.getCellData(0,2,1)).sendKeys("recruiter123");
			gp.GetElement(ExcelUtility.getCellData(0,3,0), ExcelUtility.getCellData(0,3,1)).click();
	 }
}
