package com.mcnaughty.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Channel {
	private final String name;
	private BufferedWriter output;
	private BufferedReader input;

	protected Channel(String name, BufferedWriter output, BufferedReader input)
			throws IOException {
		this.name = name;
		this.output = output;
		this.input = input;
		output.write("JOIN #" + name + "\n");
		output.flush();
	}

	public void println(String message) throws IOException {
		output.write("PRIVMSG #" + name + " " + ":" + message + "\n");
		output.flush();
	}
	
	public void partChannel(){
		
	}
}
