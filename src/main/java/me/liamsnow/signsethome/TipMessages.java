package me.liamsnow.tipmessages;

import com.sk89q.worldguard.WorldGuard;
import me.liamsnow.tipmessages.commands.*;
import me.liamsnow.tipmessages.eventhandlers.GriefProtectionEventHandler;
import me.liamsnow.tipmessages.eventhandlers.SignBreakEventHandler;
import me.liamsnow.tipmessages.eventhandlers.SignClickEventHandler;
import me.liamsnow.tipmessages.filehandlers.ConfigFileHandler;
import me.liamsnow.tipmessages.filehandlers.DataFileHandler;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public final class TipMessages extends JavaPlugin {
	public static TipMessages instance;
	public static GriefPrevention griefPrevention;

	@Override
	public void onEnable() {
		instance = this;

		//Load Config & Data
		ConfigFileHandler.init();
		DataFileHandler.init();

		//Load GriefPrevention Plugin
		Plugin griefPreventionPlugin = getServer().getPluginManager().getPlugin("GriefPrevention");
		if(griefPreventionPlugin == null || !griefPreventionPlugin.isEnabled()) {
			getLogger().severe("TipMessages was unable to find GriefPrevention dependency - Disabling");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		griefPrevention = (GriefPrevention) griefPreventionPlugin;

		//Register Commands
		getCommand("sethome").setExecutor(new SetHomeCommand());
		getCommand("stuck").setExecutor(new StuckCommand());
		getCommand("tipmessages-reload").setExecutor(new ReloadCommand());
		getCommand("tipmessages-setspawn").setExecutor(new SetSpawnCommand());
		getCommand("tipmessages-setwarplobby").setExecutor(new SetWarpLobbyCommand());
		getCommand("tipmessages-givewarplobbysign").setExecutor(new GiveWarpLobbySignCommand());

		//Register Event Handlers
		getServer().getPluginManager().registerEvents(new SignClickEventHandler(), this);
		getServer().getPluginManager().registerEvents(new SignBreakEventHandler(), this);
		getServer().getPluginManager().registerEvents(new GriefProtectionEventHandler(), this);
		//TODO remove /sethome on claim abandonment

		//Log
		getLogger().info("Enabled TipMessages!");
	}

	@Override
	public void onDisable() {
		ConfigFileHandler.save();
		DataFileHandler.save();
	}
}
