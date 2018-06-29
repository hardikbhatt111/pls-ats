package com.pls.automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

import com.pls.utilities.Config;

public class WebDriverFactory {
	public static String WebDriverFactory() {
		 Config cfg = new Config();
		String browsername = cfg.readProperty("browser");
		return browsername;
		
	}

	private static final DesiredCapabilities capabilities = new DesiredCapabilities();

	@SuppressWarnings("static-access")
	public static WebDriver getDriver() {
				
			Reporter.log("[INFO]: The test Browser is " + WebDriverFactory().toUpperCase() + " !!!", true);
            Config cfg = new Config();
			System.out.println("this is execution of driver");
				if (WebDriverFactory().equalsIgnoreCase("firefox")) {
					return getFirefoxDriver(cfg.readProperty("driverpath"));
				} else if (WebDriverFactory().equalsIgnoreCase("chrome")) {
					return getChromeDriver(cfg.readProperty("driverpath"));
				} else if (WebDriverFactory().equalsIgnoreCase("Safari")) {
					return getSafariDriver();
				} else if ((WebDriverFactory().equalsIgnoreCase("ie")) || (WebDriverFactory().equalsIgnoreCase("internetexplorer"))
						|| (WebDriverFactory().equalsIgnoreCase("internet explorer"))) {
					return getInternetExplorerDriver(cfg.readProperty("driverpath"));
				}		
			
			
		return null;
	}

	private WebDriver setRemoteDriver(Map<String, String> selConfig) {
		DesiredCapabilities cap = null;
		if (WebDriverFactory().equalsIgnoreCase("firefox")) {
			cap = DesiredCapabilities.firefox();
		} else if (WebDriverFactory().equalsIgnoreCase("chrome")) {
			cap = DesiredCapabilities.chrome();
		} else if (WebDriverFactory().equalsIgnoreCase("Safari")) {
			cap = DesiredCapabilities.safari();
		} else if ((WebDriverFactory().equalsIgnoreCase("ie")) || (WebDriverFactory().equalsIgnoreCase("internetexplorer"))
				|| (WebDriverFactory().equalsIgnoreCase("internet explorer"))) {
			cap = DesiredCapabilities.internetExplorer();
		}
		String seleniuhubaddress = selConfig.get("seleniumserverhost");
		URL selserverhost = null;
		try {
			selserverhost = new URL(seleniuhubaddress);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		cap.setJavascriptEnabled(true);
		return new RemoteWebDriver(selserverhost, cap);
	}

	private static WebDriver getChromeDriver(String driverpath) {
		System.out.println("[Info] Driver Path :: "+driverpath);
		System.setProperty("webdriver.chrome.driver", driverpath+"chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		options.addArguments("start-maximized");
		options.addArguments("--disable-web-security");
		options.addArguments("--allow-running-insecure-content");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(cap);
	}

	private static WebDriver getInternetExplorerDriver(String drivrerpath) {

		System.out.println("[Info] Driver Path :: "+drivrerpath);
		System.setProperty("webdriver.ie.driver", drivrerpath+"IEDriverServer.exe");
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability("requireWindowFocus", true);
		capabilities.setCapability("enablePersistentHover", false);
		return new InternetExplorerDriver(capabilities);
	}

	private static WebDriver getSafariDriver() {
		return new SafariDriver();
	}

	private static WebDriver getFirefoxDriver(String drivrerpath) {
	
		System.out.println("[Info] Driver Path :: "+drivrerpath);
		System.setProperty("webdriver.gecko.driver", drivrerpath+"geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
}
