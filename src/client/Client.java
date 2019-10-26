package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.JTextArea;

public class Client {
	
	private static DatagramSocket socket;
	private JTextArea area;
	
	public void start(JTextArea area){
		
		this.area = area;
		
		try {
			socket = new DatagramSocket();
			receiveMessage();
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
	
	private void receiveMessage(){
		
		Thread thread = new Thread("Client: Waiting to recive packet"){
			
			public void run(){
				
				try {
					
					while(true){
						
						byte[] rData = new byte[1024];
						DatagramPacket packet = new DatagramPacket(rData, rData.length);
						
						socket.receive(packet);
						
						String msg = new String(rData);
						msg = msg.substring(0, msg.indexOf("/e/"));
						
						System.out.println("Packet comming in from: " + packet.getAddress().getHostAddress() + 
								" on port: " + packet.getPort() + " Message: " + msg);
						
						area.append(msg + "\n");
					}
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}; thread.start();
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
