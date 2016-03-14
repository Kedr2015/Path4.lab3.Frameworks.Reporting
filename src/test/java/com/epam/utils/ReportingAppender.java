package com.epam.utils;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

public class ReportingAppender extends AppenderSkeleton{

    
    public void close() {
	
	
    }

   
    public boolean requiresLayout() {
	
	return false;
    }

    @Override
    protected void append(LoggingEvent event) {
	StringBuilder result = new StringBuilder(layout.format(event));
	String[] s = event.getThrowableStrRep();
	if (s != null) {
		for (final String value : s) {
			result.append(value).append(Layout.LINE_SEP);
		}
	}
	String finalMessage = result.toString() + "<br>";
	Reporter.log(finalMessage);
    }

}
