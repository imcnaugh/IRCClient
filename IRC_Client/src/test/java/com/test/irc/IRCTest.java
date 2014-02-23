package com.test.irc;

import java.io.IOException;
import java.net.UnknownHostException;

import com.mcnaughty.irc.Channel;
import com.mcnaughty.irc.IRCConnection;

public class IRCTest {

	public static void main(String[] args) {
		IRCTest test = new IRCTest();
		test.TestConnection();
	}

	public void TestConnection() {

		try {
			IRCConnection connection = new IRCConnection("irc.freenode.com",
					6666);
			Channel ircTestChannle = connection.getChannel("ircbottest");
			ircTestChannle.println("this is a test");
			while (true) {
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
