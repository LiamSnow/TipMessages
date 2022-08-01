package me.liamsnow.tipmessages;

import org.bukkit.ChatColor;

public class Util {

	/** picks smaller number */
	public static int min(int a, int b) {
		return a < b ? a : b;
	}

	/** picks bigger number */
	public static int max(int a, int b) {
		return a > b ? a : b;
	}

	public static String convertColorCodes(String str) {
		return ChatColor.translateAlternateColorCodes('&', str);
	}

}