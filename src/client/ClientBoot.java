package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClientBoot {
	
	private JFrame frame;
	
	private static String ip = "localhost";
	private static int port = 1234, width = 500, height = 500;
	
	public ClientBoot(){
		frame = new JFrame();
		
		frame.add(createMainPanel(width, height));
	}
	
	private JPanel createMainPanel(int width, int height){
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		panel.setSize(new Dimension(width, height));
		panel.setBackground(setColor(153, 153, 153));
		
		panel.add(createHeadPanel(0, 0, width, 100));
		panel.add(createBottomPanel(0, 320, width, 150));
		
		return panel;
	}
	
	// creates head Panel
	private JPanel createHeadPanel(int x, int y, int width, int height){
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(153, 153, 255));
		
		panel.add(createTextLabel(20, 20, "Chat Application",
				new Font("Segoe UI", 2, 30), 250, setColor(240, 240, 240)));
		
		return panel;
	}
	
	// add bottom panel with two buttons. one to start the server and one to start the client.
	private JPanel createBottomPanel(int x, int y, int width, int height){
		JPanel panel = new JPanel();
		JPanel startServerB = new JPanel();
		JPanel startClientB = new JPanel();
		
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(153, 255, 153));
		
		startServerB.setLayout(null);
		startServerB.setBounds(75, 50, 100, 50);
		startServerB.setBackground(setColor(153, 153, 255));
		startServerB.add(createTextLabel(13, -2, "Start Server",
				new Font("Segoe UI", 2, 15), 100, setColor(240, 240, 240)));
		
		startClientB.setLayout(null);
		startClientB.setBounds((75*4), 50, 100, 50);
		startClientB.setBackground(setColor(153, 153, 255));
		startClientB.add(createTextLabel(13, -2, "Start Client",
				new Font("Segoe UI", 2, 15), 100, setColor(240, 240, 240)));
		
		panel.add(startServerB);
		panel.add(startClientB);
		
		return panel;
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
	
	// returns a RGB color.
	private Color setColor(int r, int g, int b){
		Color color = new Color(r, g, b);
		return color;
	}
	
	public static void main(String[] args){
		
		ClientBoot cb = new ClientBoot();
		
		cb.frame.setSize(new Dimension(width, height));
		cb.frame.setTitle("Chat Application");
		cb.frame.setLocationRelativeTo(null);
		cb.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cb.frame.setResizable(false);
		
		cb.frame.setVisible(true);
	}
}
