package com.hb.test.rmi.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PerfectTimel extends Remote {
	long getPerfectTime() throws RemoteException;
}
