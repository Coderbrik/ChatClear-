package cc.plus.chathandlers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import cc.plus.main.CCMain;

public class DeathEventHandler implements Listener {
	FileConfiguration conf = CCMain.getPlugin().getConfig();
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			String deathFormat = conf.getString("Death Message").replaceAll("(&([a-f0-9]))", "\u00A7$2")
					.replaceAll("%playername%", player.getName());
			
			if(!e.getDeathMessage().equalsIgnoreCase(deathFormat)) {
				e.setDeathMessage(deathFormat);
			}
		}
	}
}
