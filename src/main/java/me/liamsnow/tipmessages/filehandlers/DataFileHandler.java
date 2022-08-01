package me.liamsnow.tipmessages.filehandlers;

import me.liamsnow.tipmessages.TipMessages;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static me.liamsnow.tipmessages.Constants.DATA_FILE_NAME;

public class DataFileHandler {

	private static File file;
	private static FileConfiguration data;
	public static void init() {
		load();
	}

	public static void load() {
		file = new File(TipMessages.instance.getDataFolder(), DATA_FILE_NAME);

		//Make Folder for Config File
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			TipMessages.instance.saveResource(DATA_FILE_NAME, false);
		}

		data = YamlConfiguration.loadConfiguration(file);
	}

	public static void save() {
		try {
			data.save(file);
		} catch (IOException e) {
			TipMessages.instance.getLogger().severe(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static int getTipIndex(UUID playerUUID) {
		return data.getInt(playerUUID + ".tip-index");
	}

	public static void setTipIndex(UUID playerUUID, int index) {
		data.set(playerUUID + ".tip-index", index);
	}

}
