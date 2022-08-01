package me.liamsnow.tipmessages.commands;

import me.liamsnow.tipmessages.TipMessages;
import me.liamsnow.tipmessages.TipSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ForceCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		TipSender.sendAll();

		return true;
	}

}