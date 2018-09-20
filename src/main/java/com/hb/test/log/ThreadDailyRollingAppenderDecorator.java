package com.hb.test.log;

import java.io.IOException;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Layout;

public class ThreadDailyRollingAppenderDecorator extends DailyRollingFileAppender {
	public ThreadDailyRollingAppenderDecorator(Layout layout,String filePath, String fileName, String datePattern) throws IOException {
		super(layout, filePath + fileName + ".log", datePattern);
	}
}
