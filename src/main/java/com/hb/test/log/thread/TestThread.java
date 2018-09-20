package com.hb.test.log.thread;

import org.apache.log4j.Logger;

import com.hb.test.log.ThreadLoggerFactory;

public class TestThread extends Thread {
	@Override
	public void run() {
		super.run();
		Logger logger = ThreadLoggerFactory.getLogger(Thread.currentThread().getName());
		logger.info(">>>>线程开始");
		for(int i =0; i<10; i++){
			logger.info(">>>>" + i);
			printLine();
		}
		logger.info(">>>>线程结束");
	}
	
	private void printLine(){
        Logger logger = ThreadLoggerFactory.getLogger(Thread.currentThread().getName());
	    System.out.println("----------------------------------------");
	    logger.info(">>>>-------------------------------------------");
	}
}
