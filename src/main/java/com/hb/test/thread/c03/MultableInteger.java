package com.hb.test.thread.c03;

public class MultableInteger {
	private long value = 10L;
	public void set(long value){
		this.value = value;
	}
	public long get(){
		return this.value;
	}
	
	public static class ReadInteger extends Thread {
		@Override
		public void run() {
			super.run();
		}
	}
	
	public static class WriteIneter extends Thread {
		@Override
		public void run() {
			super.run();
		}
	}
}
