package me.liamsnow.tipmessages;

import me.liamsnow.tipmessages.filehandlers.ConfigFileHandler;
import org.bukkit.scheduler.BukkitScheduler;
import sun.security.provider.ConfigFile;

public class PeriodicHandler {

	private static int taskID = Integer.MAX_VALUE;

	public static void begin() {
		if (!ConfigFileHandler.getTipPeriodically()) return;

		taskID = getScheduler().scheduleSyncRepeatingTask(TipMessages.instance, () -> {
			TipSender.sendAll();
		}, ConfigFileHandler.getTipPeriod() / 2, ConfigFileHandler.getTipPeriod());
	}

	public static void end() {
		if (taskID != Integer.MAX_VALUE) {
			getScheduler().cancelTask(taskID);
		}
	}

	public static void restart() {
		end();
		begin();
	}

	private static BukkitScheduler getScheduler() {
		return TipMessages.instance.getServer().getScheduler();
	}

}
