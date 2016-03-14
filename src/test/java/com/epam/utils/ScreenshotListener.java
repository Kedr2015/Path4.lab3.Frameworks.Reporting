package com.epam.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.epam.tests.BaseTest;

public class ScreenshotListener implements ITestListener {
    private static final Logger LOG = Logger.getLogger(ScreenshotListener.class);

    private void screenMake(ITestResult result) {
	Object inst = result.getInstance();
	if(inst==null){
	    return;
	}
	if(!(inst instanceof BaseTest)){
	    return;
	}
	BaseTest baseTest =(BaseTest) inst;
	WebDriver driver = baseTest.getDriver();
	if (driver!=null){
	    Screenshot.make(driver);
	}
    }

    @Override
    public void onFinish(ITestContext result) {

    }

    @Override
    public void onStart(ITestContext result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
	screenMake(result);
	LOG.info("Screnshot onTestFailure method: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
	LOG.info("Screnshot onTestSkipped method: " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult arg0) {

    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
    }

}
