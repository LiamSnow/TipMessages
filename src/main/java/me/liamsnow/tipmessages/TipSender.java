package me.liamsnow.tipmessages;

import me.liamsnow.tipmessages.filehandlers.ConfigFileHandler;
import me.liamsnow.tipmessages.filehandlers.DataFileHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class TipSender {


	public static void sendAll() {
		List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();

		for (Player player : players) {
			send(player);
		}
	}

	public static void send(Player player) {
		//Get the Player's Current Tip Index (and clamps it)
		UUID playerUUID = player.getUniqueId();
		int maxIndex = ConfigFileHandler.getNumberMessages() - 1;
		int curIndex = Util.min(DataFileHandler.getTipIndex(playerUUID), maxIndex);

		//Send Tip
		player.sendMessage(
				ConfigFileHandler.getMessagePrefix() +
				ConfigFileHandler.getMessage(curIndex) +
				ConfigFileHandler.getMessageSuffix()
		);

		//Increment Tip Index
		DataFileHandler.setTipIndex(playerUUID, (curIndex >= maxIndex) ? 0 : curIndex + 1);
	}

}
