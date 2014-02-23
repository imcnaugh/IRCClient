package com.test.irc;

import java.io.IOException;
import java.net.UnknownHostException;

import com.mcnaughty.irc.IRCConnection;

public class IRCTest {
	
	public void TestConnection(){
		
		try {
			IRCConnection connection = new IRCConnection("irc.freenode.net");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
