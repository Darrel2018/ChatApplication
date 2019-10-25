package server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServerBoot {
	
	private JFrame frame;
	
	public ServerBoot(int port){
		//new Server(1234);
		frame = new JFrame();
		createView();
	}
	
	private void createView(){
		frame.add(createMainPanel(500, 500));
	}
	
	// creates main panel
	private JPanel createMainPanel(int width, int height){
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		panel.setSize(new Dimension(width, height));
		panel.setBackground(setColor(153, 153, 153));
			
		panel.add(createHeadPanel(0, 0, width, 100));
		panel.add(statusPanel(0, 100, width, 220));
		panel.add(createBottomPanel(0, 320, width, 150));
		
		return panel;
	}
	
	// creates head Panel
	private JPanel createHeadPanel(int x, int y, int width, int height){
		JPanel panel = new JPanel();
			
		panel.setLayout(null);
		
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(153, 153, 255));
		
		panel.add(createTextLabel(20, 20, "Server Boot",
				new Font("Segoe UI", 2, 30), 250, setColor(240, 240, 240)));
		
		return panel;
	}
	
	private JPanel statusPanel(int x, int y, int width, int height){
		JPanel panel = new JPanel();
		JLabel statusLabel = createTextLabel(13, 70, "Status: ",
				new Font("Segoe UI", 2, 15), width, setColor(240, 240, 240));
		
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(255, 153, 153));
		
		panel.add(statusLabel);
		
		Thread thread = new Thread("Status bar"){
			
			public void run(){
				
				while(true){
					statusLabel.setText("Status: " + Server.getStatus());
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}; thread.start();
		
		return panel;
	}
	
	// creates bottom panel with buttons
	private JPanel createBottomPanel(int x, int y, int width, int height){
		JPanel panel = new JPanel();
		JPanel startServerB = new JPanel();
		
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(153, 255, 153));
		
		startServerB.setLayout(null);
		startServerB.setBounds((75*2 + 45), 50, 100, 50);
		startServerB.setBackground(setColor(153, 153, 255));
		startServerB.add(createTextLabel(13, -2, "Start Server",
					new Font("Segoe UI", 2, 15), 100, setColor(240, 240, 240)));
			
		addMouseListener(startServerB, "Server");
				
		panel.add(startServerB);
		
		return panel;
	}
	
	// adds mouse listeners to buttons
	private void addMouseListener(JPanel panel, String button_name){
		
		panel.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e){
				panel.setBackground(setColor(153, 190, 255));
			}
			
			public void mouseReleased(MouseEvent e){
				panel.setBackground(setColor(153, 153, 255));
				buttonFunc(button_name);
			}
		});
	}
	
	// Executes a buttons function.
	private void buttonFunc(String button){
		
		if(button.equalsIgnoreCase("server")){
			System.out.println("pressed server button");
			new Server(1234);
		}
	}
	
	// Returns a JLabel with the set location/font/width/color
	private JLabel createTextLabel(int x, int y, String text, Font font, int textWidth, Color color){
		JLabel textLabel = new JLabel();
		
		textLabel.setText(text);
		textLabel.setFont(font);
		textLabel.setBounds(x, y, textWidth, 50);
		
		textLabel.setForeground(color);
		
		return textLabel;
	}
	
	private Color setColor(int r, int g, int b){
		Color color = new Color(r, g, b);
		return color;
	}
	
	public static void main(String[] args){
		ServerBoot server = new ServerBoot(1234);
		server.frame.setSize(new Dimension(500, 500));
		server.frame.setLocationRelativeTo(null);
		server.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		server.frame.setResizable(false);
		server.frame.setTitle("ServerBoot");
		
		server.frame.setVisible(true);
	}
}
