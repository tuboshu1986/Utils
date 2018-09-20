package com.hb.test.inet.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ChatterClient extends Thread {
	private DatagramSocket socket;
	private byte[] buff = new byte[1000];
	private DatagramPacket datagram = new DatagramPacket(buff, 0, buff.length);
	private InetAddress serverAddress;
	
	public ChatterClient() throws UnknownHostException, SocketException{
		serverAddress = InetAddress.getByName(null);
		socket = new DatagramSocket();
		System.out.println(">>>>client started");
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			String msg = "asd-"+i;
			try {
				socket.send(Dgram.toDatagram(msg, serverAddress, 1711));
				socket.receive(this.datagram);
				System.out.println(Thread.currentThread().getName()+":"+Dgram.toString(this.datagram));
				Thread.sleep((long)(100*(Math.random()*10)));
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	public static void main(String[] args) throws UnknownHostException, SocketException {
		for(int i=0;i<10;i++){
			new ChatterClient().start();
		}
	}
	
}
