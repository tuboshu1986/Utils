package com.hb.test.inet.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ChatterServer {
	static final int INPORT = 1711;
	private byte[] buff = new byte[1000];
	private DatagramPacket datagram = new DatagramPacket(buff, 0, buff.length);
	private DatagramSocket socket;
	
	public ChatterServer() throws SocketException{
		socket = new DatagramSocket(INPORT);
		System.out.println(">>>>server started");
		try{
			while(true){
				socket.receive(this.datagram);
				System.out.println(">>>>client ["+this.datagram.getAddress()+":"+this.datagram.getPort()+"] msg:"+Dgram.toString(this.datagram));
				DatagramPacket toDatagram = Dgram.toDatagram("from server: "+Dgram.toString(this.datagram), this.datagram.getAddress(), this.datagram.getPort());
				this.socket.send(toDatagram);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void main(String[] args) throws SocketException {
		new ChatterServer();
	}
}
