package com.mcnaughty.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class IRCConnection {

	private String host;
	private int port;

	private BufferedWriter output;
	private BufferedReader input;

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
		output = new BufferedWriter(new OutputStreamWriter(
				connection.getOutputStream()));
		input = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		
		System.out.println("Connection established (with " + connection.getRemoteSocketAddress() + ")");
		
	}

	private void register() throws IOException {
		String nickname = "irctestb4";
		output.write("USER " + nickname + " tolmoon tolsun :hi\n");
		output.flush();
		output.write("NICK" + " " + nickname + "\n");
		output.flush();
	}

	public Channel getChannel(String name) throws IOException {
		return (new Channel(name, output, input));
	}
	
	public BufferedReader getBufferedReader(){
		return input;
	}
}
