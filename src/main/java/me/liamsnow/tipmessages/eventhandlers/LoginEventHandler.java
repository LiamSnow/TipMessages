package me.liamsnow.tipmessages.eventhandlers;

import me.liamsnow.tipmessages.TipMessages;
import me.liamsnow.tipmessages.TipSender;
import me.liamsnow.tipmessages.filehandlers.ConfigFileHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginEventHandler implements Listener {

	@EventHandler(priority=EventPriority.LOWEST)
	public void onLogin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		if (ConfigFileHandler.getTipOnLogin()) {
			//Send message after 1 tick (to put message under default join message)
			TipMessages.instance.getServer().getScheduler().runTaskLater(TipMessages.instance, () -> {
				TipSender.send(player);
			}, ConfigFileHandler.getLoginTipDelay());
		}
	}

}
