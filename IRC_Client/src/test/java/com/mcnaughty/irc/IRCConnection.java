package com.mcnaughty.irc;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class IRCConnection {

	private String host;
	private int port;
	
	private PrintStream output;

	public IRCConnection(String host) throws UnknownHostException, IOException {
		this(host, 6667);
	}

	public IRCConnection(String host, int port) throws UnknownHostException,
			IOException {
		this.host = host;
		this.port = port;

		connect();
		register();
	}

	private void connect() throws UnknownHostException, IOException {
		Socket connection = new Socket(host, port);
		output = new PrintStream(connection.getOutputStream());
	}

	private void register() {
		String nickname = "test";
	    String localhost = "localhost";
	    output.println( "USER" + " " + nickname + " " + localhost + " " + host + " " + nickname );
	    output.println( "NICK" + " " + nickname);
	}
}
