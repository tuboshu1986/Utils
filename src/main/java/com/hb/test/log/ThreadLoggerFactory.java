package com.hb.test.log;

import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class ThreadLoggerFactory {
	public static Logger getLogger(String logName) {
        Logger logger = null;
 
        logger = Logger.getLogger(logName);
        PatternLayout layout = new PatternLayout("[%-5p][%-22d{yyyy/MM/dd HH:mm:ssS}][%l]%m%n");
 
        if(logger.getAppender(logName) != null){
            System.out.println("已存在");
            return logger;
        }
        
        // 文件夹
        String logPath = "E:/huangbo/temp/log/";
 
        // 文件输出
        ThreadDailyRollingAppenderDecorator fileAppender = null;
 
        try {
            fileAppender = new ThreadDailyRollingAppenderDecorator(layout, logPath, logName, "yyyy-MM-dd");
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileAppender.setAppend(false);
        fileAppender.setImmediateFlush(true);
        fileAppender.setThreshold(Level.DEBUG);
        fileAppender.setName(logName);
 
        // 绑定日志登记
        //234
        logger.setLevel(Level.DEBUG);
        logger.addAppender(fileAppender);
        
        return logger;
    }
	
}
