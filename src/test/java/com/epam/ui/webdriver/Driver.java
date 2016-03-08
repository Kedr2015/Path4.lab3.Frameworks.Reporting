package com.epam.ui.webdriver;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.epam.exception.UnknownDriverTypeException;

/**
 * @author kedr
 *
 *         Working with the driver and browsers
 */
public class Driver {

    private static final String DEFAULT_WEB_DRIVER = "DEFAULT_WEB_DRIVER";
    private static DriverTypes defaultDriverType = DriverTypes.FIREFOX;
    private static HashMap<String, WebDriver> instances;

    static {
	instances = new HashMap<String, WebDriver>();
    }

    /**
     * @param name
     *            browser
     * @param driverType
     *            browser
     * @return driver - browser instance
     * 
     *         browser instance creation
     */
    public static WebDriver getWebDriverInstances(String name, DriverTypes driverType) {
	WebDriver driver;
	if (!instances.containsKey(name)) {
	    switch (driverType) {
	    case FIREFOX:
		driver = new FirefoxDriver();
		break;
	    case CHROME:
		System.setProperty("webdriver.chrome.driver",
			System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		break;
	    default:
		throw new UnknownDriverTypeException("Unknown webDriver specified: " + driverType.getDriverName());
	    }
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    instances.put(name, driver);
	} else {
	    driver = instances.get(name);
	}
	return driver;
    }

    /**
     * @param name
     *            browser
     * @return driver - browser instance
     * 
     *         browser instance creation, DriverTypes -defaultDriverType
     */
    public static WebDriver getWebDriverInstance(String name) {
	return getWebDriverInstances(name, defaultDriverType);
    }

    /**
     * @return driver - browser instance
     * 
     *         browser instance creation, DriverTypes -defaultDriverType, and
     *         browser -default
     */
    public static WebDriver getWebDriverInstance() {
	return getWebDriverInstances(DEFAULT_WEB_DRIVER, defaultDriverType);
    }
}