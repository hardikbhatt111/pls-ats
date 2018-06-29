package com.pls.automation;

//This is made by Poonam


import java.io.File;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;


import com.pls.automation.Wait;
import com.pls.utilities.Config;
public class BaseUi{

	static Config cfg = new Config();


	public static  WebDriver driver = WebDriverFactory.getDriver();
	static GetPageElements ele = new GetPageElements();
	
	protected static Wait wait = new Wait(driver,Integer.parseInt(cfg.readProperty("timeout")));
	
	public static void launchApplication() {
		//System.out.println("launch getting executed");
		Config cfg = new Config();
		String base_url = cfg.readProperty("url");
		Reporter.log("\n[INFO]: The application url is :- " + base_url, true);
		System.out.println(base_url);
		driver.manage().deleteAllCookies();
		driver.get(base_url);

	}
	
	public static void navigateToAPage(String url ) {
		driver.get(url);
	}
	protected static String getPageTitle() {
		return driver.getTitle();
	}

	protected static void logMessage(String message) {
		 Reporter.log(message, true);
	}

	protected static String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public static boolean verifyPageTitleContains(String expectedPagetitle) {
		if (((expectedPagetitle == "") || (expectedPagetitle == null) || (expectedPagetitle.isEmpty()))) {
			expectedPagetitle = getCurrentURL();
		}
		try {
			wait.waitForPageTitleToContain(expectedPagetitle);
			String actualPageTitle = getPageTitle().trim();
			logMessage(
					"ASSERTION PASSED: PageTitle for '" + actualPageTitle + "' contains: '" + expectedPagetitle + "'.");
			return true;
		} catch (TimeoutException exp) {
			String actualPageTitle = driver.getTitle().trim();
			logMessage("ASSERTION FAILED: As actual Page Title: '" + actualPageTitle
					+ "' does not contain expected Page Title : '" + expectedPagetitle + "'.");
			return false;
		}
	}

	protected WebElement getElementByIndex(List<WebElement> elementlist, int index) {
		return elementlist.get(index);
	}

	protected WebElement getElementByExactText(List<WebElement> elementlist, String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().equalsIgnoreCase(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list No element
		// exception
		if (element == null) {
		}
		return element;
	}

	protected WebElement getElementByContainsText(List<WebElement> elementlist, String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().contains(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list
		if (element == null) {
		}
		return element;
	}
	
	

	protected void switchToFrame(WebElement element) {
	
		wait.waitForElementToBeVisible(element);
		driver.switchTo().frame(element);
	}

	public void switchToFrame(int i) {
		driver.switchTo().frame(i);
	}

	public void switchToFrame(String id) {

		driver.switchTo().frame(id);
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public void navigateBackToPage() {
		driver.navigate().back();
	}

	protected Object executeJavascript(String script) {
		return ((JavascriptExecutor) driver).executeScript(script);
	}

	protected String executeJavascript1(String script) {
		
		return (String) ((JavascriptExecutor) driver).executeScript(script);
	}
	
public int executeJavascriptreturneturnInt(String script) {
		
		return (int) ((JavascriptExecutor) driver).executeScript(script);
	}

public String executeJavascriptWithStringReturn(String script) {
	return (String) ((JavascriptExecutor) driver).executeScript(script);
}

public Boolean executeJavascriptWithReturnBoolean(String script) {
	return (Boolean) ((JavascriptExecutor) driver).executeScript(script);
}

public int executeJavascriptWithIntReturn(String script) {
	return (int) ((JavascriptExecutor) driver).executeScript(script);
}

public long executeJavascriptWithReturnlong(String script) {
	return (long) ((JavascriptExecutor) driver).executeScript(script);
}

public Object executeJavascriptWithReturn(String script) {
	return ((JavascriptExecutor) driver).executeScript(script);
}

@SuppressWarnings("unchecked")
public List<WebElement> executeJavascriptWithReturnelement(String script) {
	return (List<WebElement>) ((JavascriptExecutor) driver).executeScript(script);
}


protected WebElement executeJavascriptWithReturnWebelement(String script) {
	return (WebElement) ((JavascriptExecutor) driver).executeScript(script);
}

protected void executeJavascript(String script, WebElement e) {
	((JavascriptExecutor) driver).executeScript(script, e);
}


	protected void handleAlert() {
		try {
			switchToAlert().accept();
			logMessage("Alert handled..");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("No Alert window appeared...");
		}
	}

	private Alert switchToAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public static void selectProvidedTextFromDropDown(String text, String locatorType, String locatorValue) throws InterruptedException {
		//wait.hardWait(5);
		WebElement el = ele.GetElement(locatorType, locatorValue);
		wait.waitForElementToBeVisible(el);
		scrollDown(el);
		Select sel = new Select(el);
		sel.selectByVisibleText(text);
	}

	protected void selectProvidedValueFromDropDown(WebElement el, String value) {
		wait.waitForElementToBeVisible(el);
		scrollDown(el);
		Select sel = new Select(el);
		sel.selectByValue(value);
	}

	protected void selectValueIndexFromDropDown(WebElement el, int index) {
		wait.waitForElementToBeVisible(el);
		scrollDown(el);
		Select sel = new Select(el);
		sel.selectByIndex(index);
	}

	protected String getFirstSelectedOptionFromDropdown(WebElement el) {
		wait.waitForElementToBeVisible(el);
		scrollDown(el);
		Select sel = new Select(el);
		return sel.getFirstSelectedOption().getText();
	}

	protected static void scrollDown(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	protected void scrollDown() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,10000)");
	}

	public static void scrollUp() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-10000)");
	}

	public static void hover(String locatorType, String locatorValue) throws InterruptedException {
		WebElement element = ele.GetElement(locatorType, locatorValue);
		Actions hoverOver = new Actions(driver);
		hoverOver.moveToElement(element).build().perform();
	}

	public void hoverAndClick(WebElement element) {
		Actions hoverClick = new Actions(driver);
		hoverClick.moveToElement(element).click().build().perform();
	}

	public static void hoverOnMainAndClickSubLink(WebElement mainElement, WebElement subElement) {
		Actions actions = new Actions(driver);
		Actions builder = actions.moveToElement(mainElement);
		Action b = builder.build();
		b.perform();
		// actions.moveToElement(mainElement).build().perform();
		// wait.hardWait(1);
		// actions.moveToElement(subElement);
		// actions.click().build().perform();
	}

	@SuppressWarnings("static-access")
	public static void click(String locatorType, String locatorValue) throws InterruptedException {
		wait.waitForPageToLoad();
		wait.waitForElementToAppear(ele.GetElement(locatorType, locatorValue));
		wait.waitForElementToBeVisible(ele.GetElement(locatorType, locatorValue));
		WebElement element = ele.GetElement(locatorType, locatorValue);
		try {
			wait.waitForElementToBeVisible(element);
			scrollDown(element);
			element.click();
		} catch (StaleElementReferenceException ex1) {
			wait.waitForElementToBeVisible(element);
			scrollDown(element);
			element.click();
			logMessage("Clicked Element " + element + " after catching Stale Element Exception");
		} catch (Exception ex2) {
			logMessage("Element " + element + " could not be clicked! " + ex2.getMessage());
		}
		wait.waitForPageToLoad();
	}

	public void mouseHoverJScript(String locatorType, String locatorValue) throws InterruptedException {
		WebElement HoverElement = ele.GetElement(locatorType, locatorValue);
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} "
				+ "else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		((JavascriptExecutor) driver).executeScript(mouseOverScript, HoverElement);
	}

	public void changeWindow(int i) {
		Set<String> windows = driver.getWindowHandles();
		System.out.println("Window Size::" + windows.size());
		String wins[] = windows.toArray(new String[windows.size()]);
		driver.switchTo().window(wins[i]);
	}

	public void closeWindow(int i) {
		Set<String> windows = driver.getWindowHandles();
		System.out.println("Window Size::" + windows.size());
		String wins[] = windows.toArray(new String[windows.size()]);
		driver.switchTo().window(wins[i]);
		driver.close();
	}

	public static void sendText(String locatorType, String locatorValue, String text) throws InterruptedException {
	
		WebElement e = ele.GetElement(locatorType, locatorValue);
		//wait.waitForPageToLoad();
		//wait.waitForPageToLoadCompletely();
		//wait.waitForElementToBeVisible(e);
		e.clear();
		e.sendKeys(text);

	}

	public void sendFilePath(WebElement e, String filePath) {
		//wait.waitForPageToLoad();
		e.sendKeys(filePath);
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public String getAlertText() {
		try {
			wait.hardWait(5);
			Alert alert = driver.switchTo().alert();
			String message = alert.getText();
			alert.accept();
			return message;
		} catch (NoAlertPresentException e) {
			return "No Alert is present";
		}
	}

	public void acceptAlertPopUp() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			// System.out.println("No Alert is present");
		}
	}

/*	public void hardWaitForIEBrowser(int seconds) {
		
			wait.hardWait(seconds);
	}*/

	@SuppressWarnings("static-access")
	public static void clickByJavascript(String locatorType, String locatorValue) throws InterruptedException {
wait.waitForPageToLoad();
		WebElement element = ele.GetElement(locatorType, locatorValue);
		//wait.waitForElementToBeVisible(element);
		//wait.waitForElementToBeClickable(element);
		try{			
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}
		catch(Exception e){
		//
		}
		//wait.hardWait(2);
		
	}
	
	public String replaceExtraChar(String firstString) {
		return firstString.replaceAll("[^a-zA-Z 0-9]+", "").replace(" ", "");
	}
	
	
	


}
