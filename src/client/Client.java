package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {
	
	private static DatagramSocket socket;
	
	public void start(){
		
		try {
			socket = new DatagramSocket();
		} catch (SocketException e){
			e.getStackTrace();
		}
	}
	
	public boolean hasClient(){
		
		if(socket != null){
			return true;
		}
		
		return false;
	}
	
	public void send(String msg, String ip, int port){
		
		try {
			
			msg += "/e/";
			
			byte[] rData = msg.getBytes();
			
			DatagramPacket packet = new DatagramPacket(rData, rData.length, InetAddress.getByName(ip), port);
			
			socket.send(packet);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
