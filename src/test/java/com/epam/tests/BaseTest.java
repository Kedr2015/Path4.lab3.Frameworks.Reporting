package com.epam.tests;


import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

import com.epam.ui.XmlUtils;
import com.epam.ui.pages.LoginPage;
import com.epam.ui.pages.MainMailPage;
import com.epam.ui.webdriver.Driver;
import com.epam.utils.Screenshot;
import com.epam.utils.ScreenshotListener;
import org.w3c.dom.Element;

/**
 * @author kedr
 * 
 *         Parsing a file with the parameters and the choice of driver
 */
@Listeners({HTMLReporter.class, ScreenshotListener.class})
public class BaseTest {
    private static final Logger LOG = Logger.getLogger(BaseTest.class);

    protected WebDriver driver;
    protected Element file;

    public WebDriver getDriver(){
	return 	this.driver;
    }
    
    /**
     * Driver selection
     * 
     */
    public void driverSelection() {
	driver = Driver.getWebDriverInstance();
    }

    @BeforeTest
    public void startTest() {
	System.setProperty("org.uncommons.reportng.escape-output", "false");
	DOMConfigurator.configure("log4j.xml");
	Screenshot.deleterAll();
	driverSelection();
	file = XmlUtils.parseFileXML();
	driver.get(XmlUtils.getParameterFromXML("url", file));
    }

    /**
     * Actions after the test class
     */
    @AfterTest
    public void closeBrowser() {
	// Sign Out
	new MainMailPage(driver).exitSystem();
	// Close Browser
	driver.close();

    }

    @Test
    public void authorizationCheck() {
	LOG.info("Start authorizationCheck\n");
	System.out.println("Checking login to the system.");
	boolean openMainPage = new LoginPage(driver).loginMetod(XmlUtils.initializationUser(file)).IsProfileButon();
	Assert.assertTrue(openMainPage, "Login not implemented");
	LOG.info("End authorizationCheck\n");
    }

}
