package com.hb.test.thread.c02;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.atomic.AtomicInteger;

public class SafeCounter extends UnicastRemoteObject implements RemoteInterface {
	private static final long serialVersionUID = 1L;
	private static AtomicInteger count = new AtomicInteger(100);
	private static AtomicInteger count1 = new AtomicInteger(100);
	
	public SafeCounter() throws RemoteException {
		super();
	}

	@Override
	public int getNext() {
		synchronized (this) {
			int a = count.decrementAndGet();
			try {
				Thread.sleep(((long)(Math.random()*10))*10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int b = count1.decrementAndGet();
			System.out.println(a+"-"+b);
			return a+b;
		}
	}

	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		LocateRegistry.createRegistry(2005);
		SafeCounter c = new SafeCounter();
		Naming.bind("rmi://localhost:2005/counter", c);
	}
}
