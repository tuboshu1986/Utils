package com.hb.test.thread;

import java.util.Hashtable;
import java.util.Map;

public class ThreadTest {
	public static void main(String[] args) {
		final DogPrinter p = new DogPrinter();
		for(int i=0;i<1000;i++){
			final int index = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep((long)(Math.random()*200));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					p.print(">>>>" + index);
				}
			}).start();
		}
	}
}

class DogPrinter{
	private Map<String, String> map = new Hashtable<>();
	public void print(String msg){
		map.put(msg, msg);
		System.out.println(msg);
		System.out.println(">>>>>>>>>>" + map.size());
	}
}
