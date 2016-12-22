package cc.plus.chathandlers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import cc.plus.main.CCMain;

public class JoinMessageHandler implements Listener {
	FileConfiguration conf = CCMain.getPlugin().getConfig();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		String joinFormat = conf.getString("Join Message").replaceAll("(&([a-f0-9]))", "\u00A7$2").replaceAll
				("%playername%", e.getPlayer().getName());
		if(!e.getJoinMessage().equalsIgnoreCase(joinFormat)) {
			e.setJoinMessage(joinFormat);
			
			if(conf.getConfigurationSection("playerinfo").getString(e.getPlayer().getUniqueId().toString() + ".info.chats.prefix") == null) {
				conf.getConfigurationSection("playerinfo").set(e.getPlayer().getUniqueId().toString() + ".info.chats.prefix", "");
				
				CCMain.getPlugin().saveConfig();
				CCMain.getPlugin().reloadConfig();
			}
		}
	}
}
