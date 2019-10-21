package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
	
	private DatagramSocket socket;
	
	private boolean running = false;
	
	public Server(int port){
		
		try {
			
			socket = new DatagramSocket(port);
			running = true;
			
			receive();
			
			System.out.println("Starting server on port: " + port);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void receive(){
		
		Thread thread = new Thread("Waiting to recive packet"){
			
			public void run(){
				
				try {
					
					while(running){
						
						byte[] rData = new byte[1024];
						DatagramPacket packet = new DatagramPacket(rData, rData.length);
						
						socket.receive(packet);
						
						String msg = new String(rData);
						msg = msg.substring(0, msg.indexOf("/e/"));
						
						System.out.println("Packet comming in from: " + packet.getAddress().getHostAddress() + 
								" on port: " + packet.getPort() + " Message: " + msg);
						
						Thread.sleep(100);
					}
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}; thread.start();
	}
}
