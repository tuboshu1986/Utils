package com.hb.test.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.hb.test.rmi.remote.PerfectTimel;


public class Test {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		PerfectTimel pt = (PerfectTimel)Naming.lookup("rmi://localhost:1099/PerfectTime");
		System.out.println(pt.getPerfectTime());
		
		String[] rmList = Naming.list("rmi://localhost:1099");
		for (String rm : rmList) {
			System.out.println(rm);
		}
	}
}
