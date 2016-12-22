package cc.plus.chathandlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import cc.plus.main.CCMain;

public class ChatEventHandler implements Listener {
	FileConfiguration conf = CCMain.getPlugin().getConfig();
	public boolean useFormat = conf.getBoolean("UseMF");

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {

		String messageFormat = conf.getString("MessageFormat").replaceAll("(&([a-f0-9]))", "\u00A7$2").replaceAll
				("%playername%", e.getPlayer().getName()).replaceAll("%message%", e.getMessage()).replaceAll("%prefix%",
						conf.getConfigurationSection("playerinfo").getString(e.getPlayer().getUniqueId().toString() + ".info.chats.prefix"));

		if(useFormat) {

			if(!e.getFormat().equalsIgnoreCase(messageFormat)) {
				e.setFormat(ChatColor.translateAlternateColorCodes('&', messageFormat));
			}else{
				if(e.getPlayer().hasPermission("cc.plus.color")) {
					e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
				}
			}
			
			if(e.getPlayer().hasPermission("cc.plus.defcolor")) {
				Bukkit.getServer().broadcastMessage("Has perm");
				e.setMessage(ChatColor.translateAlternateColorCodes('&', setDefForm(e.getPlayer().getUniqueId().toString(), e.getMessage())));
			}
		}
	}
	private String setDefForm(String uuid, String message) {
		String color = conf.getConfigurationSection("playerinfo").getString(uuid + ".info.chats.color");
		return color + message;
	}
}

