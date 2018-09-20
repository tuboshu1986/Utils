package com.hb.test.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hb.test.log.pkg1.Print1;
import com.hb.test.log.pkg2.Print2;
import com.hb.test.log.thread.TestThread;


public class Log4jTest {
	private static final Logger logger = LoggerFactory.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		logger.info(">>>>开始创建线程");
		for(int i=0; i<5; i++){
			new TestThread().start();
		}
		logger.info(">>>>创建线程完成");
	}
	
	public static void print(){
		logger.info(">>>>开始");
		Print1.print();
		Print2.print();
		logger.info(">>>>结束");
	}
	
}
