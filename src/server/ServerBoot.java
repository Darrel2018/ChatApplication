package server;

public class ServerBoot {
	
	private static int port = 1234;
	
	public ServerBoot(int port){
		new Server(port);
	}
	
	public static void main(String[] args){
		new ServerBoot(port);
	}
}
