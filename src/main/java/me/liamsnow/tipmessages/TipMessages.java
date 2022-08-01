package me.liamsnow.tipmessages;

import me.liamsnow.tipmessages.commands.ForceCommand;
import me.liamsnow.tipmessages.commands.ReloadCommand;
import me.liamsnow.tipmessages.eventhandlers.LoginEventHandler;
import me.liamsnow.tipmessages.filehandlers.ConfigFileHandler;
import me.liamsnow.tipmessages.filehandlers.DataFileHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class TipMessages extends JavaPlugin {
	public static TipMessages instance;

	@Override
	public void onEnable() {
		instance = this;

		//Load Config & Data
		ConfigFileHandler.init();
		DataFileHandler.init();

		//Register Commands
		getCommand("tipmessages-reload").setExecutor(new ReloadCommand());
		getCommand("tipmessages-force").setExecutor(new ForceCommand());

		//Register Event Handlers
		getServer().getPluginManager().registerEvents(new LoginEventHandler(), this);

		//Begin Period Loop
		PeriodicHandler.begin();

		//Log
		getLogger().info("Enabled TipMessages!");
	}

	@Override
	public void onDisable() {
		PeriodicHandler.end();
		ConfigFileHandler.save();
		DataFileHandler.save();
	}
}
