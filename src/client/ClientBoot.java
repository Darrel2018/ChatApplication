package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientBoot {
	
	private JFrame frame;
	private Client client;
	private JTextArea textArea;
	private JTextField fieldMessage;
	
	private static String ip = "localhost";
	private static int port = 1234, width = 500, height = 500;
	
	// Constructor.
	public ClientBoot(){
		frame = new JFrame();
		client = new Client();
		textArea = new JTextArea();
		fieldMessage = new JTextField();
		
		frame.add(createMainPanel(width, height));
	}
	
	// creates main panel
	private JPanel createMainPanel(int width, int height){
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		panel.setSize(new Dimension(width, height));
		panel.setBackground(setColor(153, 153, 153));
		
		panel.add(createHeadPanel(0, 0, width, 100));
		panel.add(createTextArea(0, 100, width, 220));
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
	
	// creates textArea
	private JPanel createTextArea(int x, int y, int width, int height){
		JPanel panel = new JPanel();
		JPanel Submit = new JPanel();
		JScrollPane scrollPane;
		
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(255, 153, 153));
		
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, 0, width, height-50);
		
		fieldMessage.setBounds(0, 170, width-100, 30);
		
		Submit.setLayout(null);
		Submit.setBounds(width-100, 170, 100, 50);
		Submit.setBackground(setColor(153, 153, 255));
		
		Submit.add(createTextLabel(25, -2, "Submit",
				new Font("Segoe UI", 2, 15), 100, setColor(240, 240, 240)));
		
		addMouseListener(Submit, "submit");
		
		panel.add(fieldMessage);
		panel.add(Submit);
		panel.add(scrollPane);
		
		return panel;
	}
	
	// returns textArea.
	public JTextArea getTextArea(){
		return textArea;
	}
	
	// creates bottom panel with buttons
	private JPanel createBottomPanel(int x, int y, int width, int height){
		JPanel panel = new JPanel();
		JPanel startClientB = new JPanel();
		
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(153, 255, 153));
		
		startClientB.setLayout(null);
		startClientB.setBounds((75*2 + 45), 50, 100, 50);
		startClientB.setBackground(setColor(153, 153, 255));
		startClientB.add(createTextLabel(13, -2, "Start Client",
				new Font("Segoe UI", 2, 15), 100, setColor(240, 240, 240)));
		
		addMouseListener(startClientB, "Client");
		
		panel.add(startClientB);
		
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
		
		if(button.equalsIgnoreCase("client")){
			System.out.println("pressed client button");
			
			if(!client.hasClient()){
				client.start(textArea);
				textArea.append("Started Client!\n");
			}
			
			client.send("New Client joined\n", ip, port);
		}
		else if(button.equalsIgnoreCase("submit")){
			
			if(!client.hasClient()){
				textArea.append("ERROR: please click the start client button.\n");
			}
			else {
				client.send(fieldMessage.getText(), ip, port);
				textArea.append(fieldMessage.getText() + "\n");
				fieldMessage.setText("");
			}
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
	
	// returns a RGB color.
	private Color setColor(int r, int g, int b){
		Color color = new Color(r, g, b);
		return color;
	}
	
	//----====Main===----
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
