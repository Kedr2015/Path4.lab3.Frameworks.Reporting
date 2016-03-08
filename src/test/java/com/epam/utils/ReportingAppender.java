package com.epam.utils;

import org.apache.log4j.AppenderSkeleton;
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
	String messenger = this.layout.format(event);
	Reporter.log(messenger);
    }

}
