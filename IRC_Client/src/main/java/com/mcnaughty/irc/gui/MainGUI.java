package com.mcnaughty.irc.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mcnaughty.irc.Channel;
import com.mcnaughty.irc.IRCConnection;

public class MainGUI {
	
	private final IRCConnection ircConnection;
	private final Channel ircChannel;

	private JFrame mainFrame = new JFrame("IRC client - proof of concept");

	private JPanel mainPanel = new JPanel(new BorderLayout());
	private JPanel southPanel = new JPanel(new FlowLayout());

	private JTextArea ircOutputArea = new JTextArea(30, 50);
	private JScrollPane scrollPane = new JScrollPane(ircOutputArea);

	private JTextField inputField = new JTextField(50);
	private JButton sendBut = new JButton(" Send ");

	public MainGUI(IRCConnection ircConnection, Channel ircChannel) {
		configureGuiComponets();
		addFieldsToSouthPanel();
		addFieldsToMainPanel();
		addFieldsToMainFrame();
		addActionListeners();
		displayMainFrame();
		
		this.ircConnection = ircConnection;
		this.ircChannel = ircChannel;
		BufferedReader input = ircConnection.getBufferedReader();
		String incomingText = "";
		while(true){
			try {
				incomingText = input.readLine();
				if(incomingText != null && !incomingText.equals("")){
					ircOutputArea.append(incomingText + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void displayMainFrame() {
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private void addActionListeners() {
		sendBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendTextInInputField();
			}
		});

		sendBut.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendTextInInputField();
				}
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
			}
		});

		inputField.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendTextInInputField();
				}
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
			}
		});
	}

	private void addFieldsToMainFrame() {
		mainFrame.getContentPane().add(mainPanel);
	}

	private void addFieldsToMainPanel() {
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
	}

	private void addFieldsToSouthPanel() {
		southPanel.add(inputField);
		southPanel.add(sendBut);
	}

	private void configureGuiComponets() {
		ircOutputArea.setEditable(false);
		ircOutputArea.setAutoscrolls(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void sendTextInInputField() {
		String text = inputField.getText();
		if (!text.equals("")) {
			try {
				ircChannel.println(text);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inputField.setText("");
		}
	}
}
