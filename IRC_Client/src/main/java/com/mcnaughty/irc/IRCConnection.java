package com.mcnaughty.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.mcnaughty.irc.util.Util;

public class IRCConnection {

	private static final int defalutPort = 6666;

	private final String hostsName = "localhost";
	private final String servcieName = "McNaughtyIRC";

	private final String host;
	private final int port;
	private String nick;

	private BufferedWriter output;
	private BufferedReader input;

	public IRCConnection(String host) throws UnknownHostException, IOException {
		this(host, defalutPort, Util.generateRandomNick());
	}

	public IRCConnection(String host, int port) throws UnknownHostException, IOException {
		this(host, port, Util.generateRandomNick());
	}

	public IRCConnection(String host, String nick) throws UnknownHostException,
			IOException {
		this(host, defalutPort, nick);
	}

	public IRCConnection(String host, int port, String nick)
			throws UnknownHostException, IOException {
		this.host = host;
		this.port = port;
		this.nick = nick;

		connect();
		register();
	}

	public Channel getChannel(String name) throws IOException {
		return (new Channel(name, output, input));
	}
	
	public BufferedReader getBufferedReader(){
		return input;
	}

	
	private void connect() throws UnknownHostException, IOException {
		Socket connection = new Socket(host, port);
		output = new BufferedWriter(new OutputStreamWriter(
				connection.getOutputStream()));
		input = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
	}

	private void register() throws IOException {
		output.write("USER " + nick + " " + hostsName + "  " + servcieName
				+ " " + ":" + nick + "\n");
		output.flush();
		output.write("NICK" + " " + nick + "\n");
		output.flush();
	}
}
