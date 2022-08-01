package me.liamsnow.tipmessages.commands;

import me.liamsnow.tipmessages.PeriodicHandler;
import me.liamsnow.tipmessages.TipMessages;
import me.liamsnow.tipmessages.filehandlers.ConfigFileHandler;
import me.liamsnow.tipmessages.filehandlers.DataFileHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		TipMessages.instance.getServer().broadcastMessage("Reloading TipMessages Config & Data!");

		ConfigFileHandler.load();
		DataFileHandler.load();
		PeriodicHandler.restart();

		return true;
	}

}