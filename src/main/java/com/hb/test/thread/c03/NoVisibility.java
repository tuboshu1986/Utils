package com.hb.test.thread.c03;

public class NoVisibility {
	private static boolean ready;
	private static int num;
	
	private static class ReaderThread extends Thread{
		@Override
		public void run() {
			while(!ready){
				Thread.yield();
			}
			System.out.println(num);
		}
	}
	
	public static void main(String[] args) {
		new NoVisibility.ReaderThread().start();
		NoVisibility.num = 100;
		NoVisibility.ready = true;
	}
}
