package com.epam.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
    private static final String PATH_TO_REPORT = "test-output/html/";
    private static final String SCREENSHOTS_FOLDER = "screenshots";
    private static final Logger LOG = Logger.getLogger(Screenshot.class);

    public static void deleterAll() {
	File derectory = new File(PATH_TO_REPORT + SCREENSHOTS_FOLDER);
	LOG.info("Delete file " + derectory.getAbsolutePath());
	File[] files = derectory.listFiles();
	if (files != null && files.length > 0) {
	    for (File file : files) {
		if (!file.delete()) {
		    LOG.info("Cannot delete file " + file);
		}
	    }
	}
    }

    public static void make(WebDriver driver) {
	if (driver == null) {
	    return;
	}
	try {
	    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFileToDirectory(screenshot, new File(PATH_TO_REPORT + SCREENSHOTS_FOLDER));
	    String logMas = "<a href='" + SCREENSHOTS_FOLDER + "/" + screenshot.getName() + "'>See ScreenShot</a>";
	    LOG.info(logMas);
	} catch (Exception e) {
	    LOG.error("Error: " + e);
	    throw new RuntimeException(e);
	}
    }
}
