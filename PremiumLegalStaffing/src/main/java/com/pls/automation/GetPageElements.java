package com.pls.automation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.pls.utilities.*;

public class GetPageElements extends BaseUi {
	public WebDriver driver = BaseUi.driver;
	String pageName;
	public int implicitTimeReset = 5000;

	protected void waitForElementToAppear(String loc, String loctype) throws InterruptedException {
		wait.waitForElementToAppear(GetElement(loc,loctype));

	}

	/*protected void _waitForElementToDisappear(String elementToken, String replacement) {
		int i = 0;
		int initTimeout = wait.getTimeout();
		wait.resetImplicitTimeout(20);
		int count;
		while (i <= 20) {
			if (replacement.isEmpty())
				count = elements(elementToken).size();
			else
				count = elements(elementToken, replacement).size();
			if (count == 0)
				break;
			i += 2;
		}
		wait.resetImplicitTimeout(initTimeout);
	}

	protected void waitForElementToDisappear(String elementToken) {
		_waitForElementToDisappear(elementToken, "");
	}

	protected void waitForElementToDisappear(String elementToken, String replacement) {
		_waitForElementToDisappear(elementToken, replacement);
	}
*/
	protected void isStringMatching(String actual, String expected) {
		logMessage("Comparing 2 strings");
		logMessage("EXPECTED STRING :" + expected);
		logMessage("ACTUAL STRING :" + actual);
		Assert.assertEquals(actual, expected, "The strings does not match!!!");
		logMessage("String compare ASSERTION passed.");
	}

	public boolean isElementDisplayed(String loc, String loctype) throws InterruptedException {
		
		Wait.waitForPageToLoad();
		wait.hardWait(3);
		boolean result;
		try
		{
		wait.waitForElementToBeVisible(GetElement(loc,loctype));
		result = GetElement(loc,loctype).isDisplayed();
		SoftAssert softAssertion= new SoftAssert();
		softAssertion.assertTrue(result, "ASSERTION FAILED: element '" + loc + "with text " + loctype
				+ "' is not displayed.");
		logMessage("ASSERTION PASSED: element " + loc + " with text " + loctype + " is displayed.");
		//return result;
		}
		catch(Exception e)
		{
		 result = false;
			//return result;
			logMessage("ASSERTION FAILED: element"+ loc +"with text" + loctype + " is not displayed.");
		}
		return result;
		//return result;
	}

	protected boolean isElementNotDisplayed(String loc, String loctype) throws InterruptedException {
		wait.waitForElementToBeVisible(GetElement(loc,loctype));
		boolean result = GetElement(loc,loctype).isDisplayed();
		assertTrue(!result,
				"ASSERTION FAILED: element '" + loc + "with text " + loctype + "' is displayed.");
		logMessage("ASSERTION PASSED: element " + loc + " with text " + loctype
				+ " is  not displayed.");
		return result;
	}

	protected void verifyElementText(String loc, String loctype, String expectedText) throws InterruptedException {
		wait.waitForElementToBeVisible(GetElement(loc,loctype));
		assertEquals(replaceExtraChar(GetElement(loc,loctype).getText()), replaceExtraChar(expectedText),
				"ASSERTION FAILED: element '" + loc + "' Text is not as expected: ");
		logMessage("ASSERTION PASSED: element " + loc + " is visible and Text is " + expectedText);
	}

	protected void verifyElementTextContains(String loc, String loctype, String expectedText) throws InterruptedException {
		wait.waitForElementToBeVisible(GetElement(loc,loctype));
		assertThat("ASSERTION FAILED: element '" + loc + "' Text is not as expected: ",
				replaceExtraChar(GetElement(loc,loctype).getText()), containsString(expectedText));
		logMessage("ASSERTION PASSED: element " + loc + " is visible and Text is " + expectedText);
	}


	protected void doHoverOverElement(String loc, String loctype) throws InterruptedException {
		Actions builder = new Actions(driver);
		builder.moveToElement(GetElement(loc,loctype)).build().perform();
	}

	protected boolean isElementEnabled(String loc, String loctype, boolean expected) throws InterruptedException {
		wait.waitForElementToBeVisible(GetElement(loc,loctype));
		boolean result = expected && GetElement(loc,loctype).isEnabled();
		assertTrue(result, "ASSERTION FAILED: element '" + loc + "' is  ENABLED :- " + !expected);
		logMessage("ASSERTION PASSED: element " + loc + " is enabled :- " + expected);
		return result;
	}

/*	protected By getLocator(String elementToken) {
		return getLocator(elementToken, "");
	}

	protected By getLocator(String elementToken, String replacement) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	protected By getLocator(String elementToken, String replacement1, String replacement2) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceFirst("\\$\\{.+?\\}", replacement1);
		locator[2] = locator[2].replaceFirst("\\$\\{.+?\\}", replacement2);
		return getBy(locator[1].trim(), locator[2].trim());
	}*/

	public boolean matchGivenPatternWithProvidedText(String pattern, String text) {
		Matcher matcher = Pattern.compile(pattern).matcher(text);
		return matcher.matches();
	}

	public String replaceExtraChar(String firstString) {
		return firstString.replaceAll("[^a-zA-Z 0-9]+", "").replace(" ", "");
	}

	public String replacenumber(String firstString) {
		return firstString.replaceAll("[^a-zA-Z]+", "").replace(" ", "");
	}

	public WebElement GetElement(String locatorType, String locatorValue) throws InterruptedException {
		Wait.waitForPageToLoad();
		
		/*System.out.println("loc type"+locatorType);
		System.out.println("loc value"+locatorValue);*/
		
		switch (locatorType) {
		case "id":
			return driver.findElement(By.id(locatorValue));
		case "xpath":
			return driver.findElement(By.xpath(locatorValue));
		case "css":
			return driver.findElement(By.cssSelector(locatorValue));
		case "name":
			return driver.findElement(By.name(locatorValue));
		case "classname":
			return driver.findElement(By.className(locatorValue));
		case "linktext":
			return driver.findElement(By.linkText(locatorValue));
		default:
			return driver.findElement(By.id(locatorValue));
		}

	}
	
	
public List<WebElement> GetElements(String locatorType, String locatorValue) {
		
	
		switch (locatorType) {
		case "id":
			return driver.findElements(By.id(locatorValue));
		case "xpath":
			return driver.findElements(By.xpath(locatorValue));
		case "css":
			return driver.findElements(By.cssSelector(locatorValue));
		case "name":
			return driver.findElements(By.name(locatorValue));
		case "classname":
			return driver.findElements(By.className(locatorValue));
		case "linktext":
			return driver.findElements(By.linkText(locatorValue));
		default:
			return driver.findElements(By.id(locatorValue));
		}

	}
	
	public void handleSavePopUp(){
		wait.resetImplicitTimeout(implicitTimeReset);
		wait.hardWait(2);
		

		wait.waitForElementToBeVisible(driver.findElement(By.xpath("//input[@value='OK']")));
		//clickByJavascript(driver.findElement(By.xpath("//input[@value='OK']")));
		
	}
	
	public void handleConfirmationPopup(){
		
		wait.hardWait(2);
	
	
		wait.waitForElementToBeVisible(driver.findElement(By.cssSelector("[value='Yes']")));
		//clickByJavascript(driver.findElement(By.cssSelector("[value='Yes']")));
	}

}

