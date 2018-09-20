package com.hb.test.log.pkg1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Print1 {
	private static final Logger logger = LoggerFactory.getLogger(Print1.class);
	public static void print(){
		logger.info(">>>>开始");
		System.out.println("print1");
		logger.info(">>>>结束");
	}
}
