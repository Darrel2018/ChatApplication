package client;

public class ClientBoot {
	
	private static String ip = "localhost";
	private static int port = 1234;
	
	public static void main(String[] args){
		
		Client.start();
		Client.send("Client Test", ip, port);
	}
}
