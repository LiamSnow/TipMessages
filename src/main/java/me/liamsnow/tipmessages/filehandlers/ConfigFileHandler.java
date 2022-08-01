package me.liamsnow.tipmessages.filehandlers;

import me.liamsnow.tipmessages.TipMessages;
import me.liamsnow.tipmessages.Util;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static me.liamsnow.tipmessages.Constants.CONFIG_FILE_NAME;
import static me.liamsnow.tipmessages.Constants.FORCE_OVERWRITE_CONFIG_FILE;

public class ConfigFileHandler {

	private static File file;
	private static FileConfiguration config;

	public static void init() {
		load();
	}

	public static void load() {
		file = new File(TipMessages.instance.getDataFolder(), CONFIG_FILE_NAME);
		boolean fileExists = file.exists();

		//Make Folder for Config File
		if (!fileExists) {
			file.getParentFile().mkdirs();
		}

		//Create Config File (& Overwrite if Asked)
		if (FORCE_OVERWRITE_CONFIG_FILE || !fileExists) {
			TipMessages.instance.saveResource(CONFIG_FILE_NAME, FORCE_OVERWRITE_CONFIG_FILE);
		}

		config = YamlConfiguration.loadConfiguration(file);
	}

	public static void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			TipMessages.instance.getLogger().severe(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static boolean getTipOnLogin() {
		return config.getBoolean("tip-on-login");
	}

	public static int getLoginTipDelay() {
		return config.getInt("tip-login-delay");
	}

	public static boolean getTipPeriodically() {
		return config.getBoolean("tip-periodically");
	}

	public static int getTipPeriod() {
		return config.getInt("tip-period");
	}

	public static String getMessagePrefix() {
		return Util.convertColorCodes(config.getString("message-prefix"));
	}

	public static String getMessageSuffix() {
		return Util.convertColorCodes(config.getString("message-suffix"));
	}

	private static List<String> getMessages() {
		return config.getStringList("messages");
	}

	public static String getMessage(int index) {
		return Util.convertColorCodes(getMessages().get(index));
	}

	public static int getNumberMessages() {
		return getMessages().size();
	}

}
