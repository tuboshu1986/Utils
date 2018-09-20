package com.hb.test.log.pkg2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Print2 {
	private static final Logger logger = LoggerFactory.getLogger(Print2.class);
	public static void print(){
		logger.info(">>>>开始");
		System.out.println("print2");
		logger.info(">>>>结束");
	}
}
