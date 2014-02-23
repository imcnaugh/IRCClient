package com.mcnaughty.irc;

import java.io.PrintStream;

public class Channel {
	private String name;
	private PrintStream output;

	protected Channel(String name, PrintStream output) {
		this.name = name;
		this.output = output;
		output.println("JOIN" + " " + "#" + name);
	}

	public void println(String message) {
		output.println("PRIVMSG" + " " + "#" + name + " " + ":" + message);
	}

}
