package com.hb.test.inet.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class Dgram {
	public static DatagramPacket toDatagram(String s, InetAddress address, int destPort){
		byte[] buff = s.getBytes();
		return new DatagramPacket(buff, buff.length, address, destPort);
	}
	
	public static String toString(DatagramPacket datagram){
		return new String(datagram.getData(), 0, datagram.getLength());
	}
	
}
 