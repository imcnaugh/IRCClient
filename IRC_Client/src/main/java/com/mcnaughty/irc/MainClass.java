package com.mcnaughty.irc;

import java.io.IOException;
import java.net.UnknownHostException;

import com.mcnaughty.irc.gui.MainGUI;

public class MainClass {

	private IRCConnection ircConnection;
	private Channel ircChannel;
	private MainGUI gui;

	public static void main(String[] args) {
		new MainClass();
	}

	private MainClass() {
		try {
			ircConnection = new IRCConnection("irc.freenode.com");
			ircChannel = ircConnection.getChannel("ircbottest");
			gui = new MainGUI(ircConnection, ircChannel);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
