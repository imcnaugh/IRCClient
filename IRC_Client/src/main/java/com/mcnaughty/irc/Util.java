package com.mcnaughty.irc;

public class Util {

	public static String generateRandomNick() {
		return "guest_" + ((int) (Math.random() * 1000));
	}

}
