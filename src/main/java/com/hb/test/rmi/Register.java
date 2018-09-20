package com.hb.test.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.hb.test.rmi.local.PerfectTime;

public class Register {
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException, NamingException {
		LocateRegistry.createRegistry(1099);
		//System.setSecurityManager(new RMISecurityManager());
		PerfectTime pt = new PerfectTime();
		Context namingContext = new InitialContext();
		namingContext.bind("rmi://localhost:1099/PerfectTime", pt);
	}
}
