package com.pls.functionalTests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pls.automation.BaseUi;
import com.pls.automation.GetPageElements;
import com.pls.pages.Login;
import com.pls.utilities.Config;
import com.pls.utilities.ExcelUtility;

import org.testng.annotations.BeforeClass;
import org.junit.Assert;
import org.testng.annotations.AfterTest;

public class CandidateCreation {
	
	
	GetPageElements gp = new GetPageElements();
	Config cfg = new Config();
	Login l = new Login();
	
  @Test
  public void TC_01_Login() throws InterruptedException, Exception {
	 l.UserLogin();
	/*gp.GetElement(ExcelUtility.getCellData(0,1,0), ExcelUtility.getCellData(0,1,1)).sendKeys("project.ats@premierlegalstaffing.com");
	gp.GetElement(ExcelUtility.getCellData(0,2,0), ExcelUtility.getCellData(0,2,1)).sendKeys("recruiter123");
	gp.GetElement(ExcelUtility.getCellData(0,3,0), ExcelUtility.getCellData(0,3,1)).click();*/
  }
	/*for(int i=1;i<11;i++)
		{*/
	//candidates page starts here
/*	gp.isElementDisplayed(ExcelUtility.getCellData(1,1,0), ExcelUtility.getCellData(1,1,1));
	
	try
	{
	BaseUi.hover(ExcelUtility.getCellData(1,1,0), ExcelUtility.getCellData(1,1,1));
	}
	catch(Exception e)
	{
		System.out.println("running into exception and scrolling up");
		BaseUi.scrollUp();
		BaseUi.hover(ExcelUtility.getCellData(1,1,0), ExcelUtility.getCellData(1,1,1));
	}
	BaseUi.clickByJavascript(ExcelUtility.getCellData(1,2,0), ExcelUtility.getCellData(1,2,1));
	
	//verify candidates page loaded
	Assert.assertTrue(BaseUi.verifyPageTitleContains("MY CANDIDATES"));
	
	
	
	Thread.sleep(5000);
	BaseUi.click(ExcelUtility.getCellData(1,3,0), ExcelUtility.getCellData(1,3,1));
	//Thread.sleep(5000);
	System.out.println(gp.isElementDisplayed(ExcelUtility.getCellData(1,4,0), ExcelUtility.getCellData(1,4,1)));
	BaseUi.click(ExcelUtility.getCellData(1,4,0), ExcelUtility.getCellData(1,4,1));*/
  @Test
  public void TC_02_CreateCandidate() throws InterruptedException, Exception {
	  SoftAssert softAssertion= new SoftAssert();
	for(int i=1;i<11;i++)
	{
     ExcelUtility.getCallerClassName();    
     BaseUi.navigateToAPage("http://plsats-live.intelliswift.co.in/candidates/new");
	//Assert.assertTrue(BaseUi.verifyPageTitleContains("PLS-ATS"));
	softAssertion.assertTrue(BaseUi.verifyPageTitleContains("PLS-ATS"));
	BaseUi.selectProvidedTextFromDropDown("Miss", ExcelUtility.getCellData(1,5,0), ExcelUtility.getCellData(1,5,1));
	BaseUi.sendText(ExcelUtility.getCellData(1,6,0), ExcelUtility.getCellData(1,6,1), ExcelUtility.getCellData(2,i,1));
	BaseUi.sendText(ExcelUtility.getCellData(1,7,0), ExcelUtility.getCellData(1,7,1), ExcelUtility.getCellData(2,i,2));
	BaseUi.sendText(ExcelUtility.getCellData(1,8,0), ExcelUtility.getCellData(1,8,1), ExcelUtility.getCellData(2,i,3));
	//contact details
	BaseUi.click(ExcelUtility.getCellData(1,9,0), ExcelUtility.getCellData(1,9,1));
	BaseUi.sendText(ExcelUtility.getCellData(1,10,0), ExcelUtility.getCellData(1,10,1), ExcelUtility.getCellData(1,10,2));
	BaseUi.sendText(ExcelUtility.getCellData(1,11,0), ExcelUtility.getCellData(1,11,1), ExcelUtility.getCellData(1,11,2));
	BaseUi.sendText(ExcelUtility.getCellData(1,12,0), ExcelUtility.getCellData(1,12,1), ExcelUtility.getCellData(1,12,2));
	
	
	BaseUi.selectProvidedTextFromDropDown(ExcelUtility.getCellData(1,13,2), ExcelUtility.getCellData(1,13,0), ExcelUtility.getCellData(1,13,1));
	BaseUi.selectProvidedTextFromDropDown(ExcelUtility.getCellData(1,14,2), ExcelUtility.getCellData(1,14,0), ExcelUtility.getCellData(1,14,1));
	
	
	//details
	BaseUi.click(ExcelUtility.getCellData(1,15,0), ExcelUtility.getCellData(1,15,1));
	BaseUi.click(ExcelUtility.getCellData(1,16,0), ExcelUtility.getCellData(1,16,1));
	BaseUi.sendText(ExcelUtility.getCellData(1,17,0), ExcelUtility.getCellData(1,17,1), ExcelUtility.getCellData(1,17,2));
	BaseUi.click(ExcelUtility.getCellData(1,18,0), ExcelUtility.getCellData(1,18,1));
	
	//practice
	BaseUi.clickByJavascript(ExcelUtility.getCellData(1,19,0), ExcelUtility.getCellData(1,19,1));
	BaseUi.selectProvidedTextFromDropDown(ExcelUtility.getCellData(1,20,2), ExcelUtility.getCellData(1,20,0), ExcelUtility.getCellData(1,20,1));
	
	//general technology
	BaseUi.click(ExcelUtility.getCellData(1,21,0), ExcelUtility.getCellData(1,21,1));
	BaseUi.selectProvidedTextFromDropDown(ExcelUtility.getCellData(1,22,2), ExcelUtility.getCellData(1,22,0), ExcelUtility.getCellData(1,22,1));
	
	
	//position type
	BaseUi.click(ExcelUtility.getCellData(1,23,0), ExcelUtility.getCellData(1,23,1));
	BaseUi.click(ExcelUtility.getCellData(1,24,0), ExcelUtility.getCellData(1,24,1));
	//BaseUi.sendText(ExcelUtility.getCellData(1,25,0), ExcelUtility.getCellData(1,25,1), ExcelUtility.getCellData(1,25,2));
	
	//desired regions
	BaseUi.click(ExcelUtility.getCellData(1,26,0), ExcelUtility.getCellData(1,26,1));
	BaseUi.click(ExcelUtility.getCellData(1,27,0), ExcelUtility.getCellData(1,27,1));
	//BaseUi.sendText(ExcelUtility.getCellData(1,28,0), ExcelUtility.getCellData(1,28,1), ExcelUtility.getCellData(1,28,2));
	
	//desired city	
	BaseUi.sendText(ExcelUtility.getCellData(1,29,0), ExcelUtility.getCellData(1,29,1), ExcelUtility.getCellData(1,29,2));
	BaseUi.click(ExcelUtility.getCellData(1,30,0), ExcelUtility.getCellData(1,30,1));
	//desired state	
	BaseUi.sendText(ExcelUtility.getCellData(1,31,0), ExcelUtility.getCellData(1,31,1), ExcelUtility.getCellData(1,31,2));
	BaseUi.click(ExcelUtility.getCellData(1,32,0), ExcelUtility.getCellData(1,32,1));
	
	//click on save link
	BaseUi.click(ExcelUtility.getCellData(1,33,0), ExcelUtility.getCellData(1,33,1));
	
	//verify success message
	gp.isElementDisplayed(ExcelUtility.getCellData(1,34,0), ExcelUtility.getCellData(1,34,1));
	softAssertion.assertAll();
	}
	
	
  }
  @BeforeClass
  public void beforeClass() {
	 
	  BaseUi.launchApplication();
  }

  @AfterTest
  public void afterTest() {
	  
  }

}
