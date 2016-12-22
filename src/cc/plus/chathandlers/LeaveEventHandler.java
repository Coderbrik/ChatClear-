package cc.plus.chathandlers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import cc.plus.main.CCMain;

public class LeaveEventHandler implements Listener {
	FileConfiguration conf = CCMain.getPlugin().getConfig();
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		String leaveMessage = conf.getString("Leave Message").replaceAll("(&([a-f0-9]))", "\u00A7$2").replaceAll
				("%playername%", e.getPlayer().getName());
		if(!e.getQuitMessage().equalsIgnoreCase(leaveMessage)) {
			e.setQuitMessage(leaveMessage);
		}
	}
}
	