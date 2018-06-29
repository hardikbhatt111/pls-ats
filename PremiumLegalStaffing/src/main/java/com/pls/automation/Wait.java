package com.pls.automation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {

	static WebDriver driver;
	WebDriverWait wait;
	
	 private static JavascriptExecutor js;
	  static String pageLoadStatus = null;
	  int timeout;

	public Wait(WebDriver driver,int timeout) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, timeout);
		this.timeout = timeout;
	}

	/**
	 * Returns webElement found by the locator if element is visible
	 *
	 * @param locator
	 * @return
	 */
	public WebElement getWhenVisible(By locator) {
		WebElement element;
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	public WebElement getWhenClickable(By locator) {
		WebElement element;
		element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}

	public boolean waitForPageTitleToBeExact(String expectedPagetitle) {
		return wait.until(ExpectedConditions.titleIs(expectedPagetitle)) != null;
	}

	public boolean waitForPageTitleToContain(String expectedPagetitle) {
		return wait.until(ExpectedConditions.titleContains(expectedPagetitle)) != null;
	}

	public WebElement waitForElementToBeVisible(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForFrameToBeAvailableAndSwitchToIt(By locator) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public List<WebElement> waitForElementsToBeVisible(List<WebElement> elements) {
		return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public boolean waitForElementToBeInVisible(By locator) {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)) != null;
	}

	public WebElement waitForElementToBeClickable(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void clickWhenReady(By locator) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public void waitForPageTitleToAppearCompletely(String txtPageTitle) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.titleIs(txtPageTitle));
	}
	
	public Alert waitForAlertTobePresentAndAcceptIt() {
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForMsgToastToDisappear() {
		int i = 0;
		resetImplicitTimeout(1);
		try {
			while (driver.findElement(By.className("toast-message")).isDisplayed() && i <= timeout) {
				hardWait(1);
				i++;
			}
		} catch (Exception e) {
		}
		resetImplicitTimeout(timeout);
	}

	public void waitForElementToDisappear(WebElement element) {
		int i = 0;
		resetImplicitTimeout(2);
		try {
			while (element.isDisplayed() && i <= timeout) {
				hardWait(1);
				i++;
			}
		} catch (Exception e) {
		}
		resetImplicitTimeout(timeout);
	}

	public void resetImplicitTimeout(int newTimeOut) {
		try {
			driver.manage().timeouts().implicitlyWait(newTimeOut, TimeUnit.SECONDS);
		} catch (Exception e) {
		}
	}

	
	public void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public int getTimeout() {
		return timeout;
	}

	public void waitForElementToAppear(WebElement element) {
		int i = 0;
	//	System.out.println("waiting For Element To Appear");
		//System.out.println("element.isDisplayed()===" + element.isDisplayed());
		resetImplicitTimeout(2);
		// hardWait(1);
		try {
			//System.out.println("try");
			while (!(element.isDisplayed()) && i <= timeout) {
				//System.out.println("while");
				hardWait(1);
				i++;
				//System.out.println(i + " Seconds");
			}
		} catch (StaleElementReferenceException e) {
			//System.out.println("exception thrown");
		}
		//System.out.println("exiting loop");
		resetImplicitTimeout(timeout);
		//System.out.println("exiting function");
	}
	
  /* public void waitForLoad() {
	 wait.until((ExpectedCondition<Boolean>) wd ->
	                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	    }*/

	
	public static void waitForPageToLoad() {
	    do {
	     js = (JavascriptExecutor) driver;
	      pageLoadStatus = (String)js.executeScript("return document.readyState");
	      //System.out.print(".");
	    } while ( !pageLoadStatus.equals("complete") );
	   // System.out.println();
	  //  System.out.println("Page Loaded.");
	  }
}
