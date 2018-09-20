package com.hb.test.rmi.local;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.hb.test.rmi.remote.PerfectTimel;

public class PerfectTime extends UnicastRemoteObject implements PerfectTimel {
	private static final long serialVersionUID = 1L;

	public PerfectTime() throws RemoteException {
		super();
	}

	@Override
	public long getPerfectTime() throws RemoteException {
		return System.currentTimeMillis();
	}

}
