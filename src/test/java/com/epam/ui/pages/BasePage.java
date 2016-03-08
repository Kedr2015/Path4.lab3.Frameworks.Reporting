package com.epam.ui.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

/**
 * @author kedr
 * 
 *         Base Page Page which is taken as a basis for all the others
 */
public class BasePage {
    private static final Logger LOG = Logger.getLogger(BasePage.class);
    protected Actions builder;
    protected WebDriver driver;
    protected JavascriptExecutor executor;

    /**
     * Transfer driver instance in the constructor
     * 
     * @param driver
     *            -webdriver
     */
    public BasePage(WebDriver driver) {
	DOMConfigurator.configure("log4j.xml");
	this.driver = driver;
	PageFactory.initElements(driver, this);
	builder = new Actions(driver);
	executor = (JavascriptExecutor) driver;
    }

    /**
     * The method checks the item
     * 
     * @return - The presence of the name field on the page
     */
    public boolean isElementDisplayed(WebElement nameElement) {
	LOG.info("Start isElementDisplayed");
	try {
	    return nameElement.isDisplayed();
	} catch (NoSuchElementException e) {
	    return false;
	}
    }

    /**
     * Method enters a value in the desired item
     * 
     * @param nameElement
     *            -The selected item
     * @param text
     *            - The selected text
     */
    public void inputText(WebElement nameElement, String text) {
	LOG.info("Start inputText");
	builder.moveToElement(nameElement).click().click().sendKeys(text).perform();
    }

    /**
     * Method click on the selected item
     * 
     * @param nameElement
     *            -The selected item
     */
    public void clickElement(WebElement nameElement) {
	LOG.info("Start clickElement");
	builder.moveToElement(nameElement).click().perform();
	checkDialogBox();
    }

    /**
     * Bypass dialog
     */
    public void checkDialogBox() {
	LOG.info("Start checkDialogBox");
	try {
	    Alert alert = driver.switchTo().alert();
	    alert.accept();
	} catch (NoAlertPresentException ex) {
	}
    }
}
