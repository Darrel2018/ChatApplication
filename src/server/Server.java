package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
	
	private DatagramSocket socket;
	
	private boolean running = false;
	private static String status;
	private int port1 = 0, port2 = 0;
	
	// Constructor.
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
	
	// dedicates two ports to variables for later use.
	private void setPort(int port){
		
		if(port1 == 0){
			port1 = port;
			System.out.println("port1 set");
		}
		else if(port2 == 0 && port != port1){
			port2 = port;
			System.out.println("port2 set");
		}
	}
	
	// receives information from the clients and then uses that information.
	private void receive(){
		
		Thread thread = new Thread("Server: Waiting to recive packet"){
			
			public void run(){
				
				try {
					
					while(running){
						
						byte[] rData = new byte[1024];
						DatagramPacket packet = new DatagramPacket(rData, rData.length);
						
						socket.receive(packet);
						
						String msg = new String(rData);
						msg = msg.substring(0, msg.indexOf("/e/"));
						
						status = "Packet comming in from: " + packet.getAddress().getHostAddress() + " on port: " + packet.getPort();
						
						System.out.println("Packet comming in from: " + packet.getAddress().getHostAddress() + 
								" on port: " + packet.getPort() + " Message: " + msg);
						
						setPort(packet.getPort());
						
						if(packet.getPort() != port1){
							sendMessages(msg, "localhost", port1);
						}
						else if(packet.getPort() != port2){
							sendMessages(msg, "localhost", port2);
						}
					}
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}; thread.start();
	}
	
	// sends messages to the clients.
	private void sendMessages(String msg, String ip, int port){
		 
		try {
			
			msg += "/e/";
			
			byte[] rData = msg.getBytes();
			
			DatagramPacket packet = new DatagramPacket(rData, rData.length, InetAddress.getByName(ip), port);
			
			socket.send(packet);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	// returns the status of the server.
	public static String getStatus(){
		return status;
	}
}
