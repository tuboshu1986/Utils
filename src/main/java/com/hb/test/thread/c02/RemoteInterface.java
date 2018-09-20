package com.hb.test.thread.c02;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
	int getNext() throws RemoteException ;
}
