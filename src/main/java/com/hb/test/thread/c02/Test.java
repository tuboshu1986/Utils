package com.hb.test.thread.c02;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Test extends Thread {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Test().start();
		}
	}
	
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			try {
				RemoteInterface c = (RemoteInterface)Naming.lookup("rmi://localhost:2005/counter");
				System.out.println(c.getNext());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
		}
	}
}
