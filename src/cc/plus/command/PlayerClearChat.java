package cc.plus.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.plus.main.ConfigManager;

public class PlayerClearChat extends ConfigManager implements CommandExecutor {
	int clearSize = super.conf.getInt("ClearAmount");
	
	public boolean onCommand(CommandSender cs, Command cm, String alias, String[] args) {
		if(cs instanceof Player) {
			if(cm.getName().equalsIgnoreCase("pcc")) {
				clearPlayerChat(cs.getName());
				
				cs.sendMessage(super.notifyCleared("p", cs.getName()));
			}
		}
		return true;
	}
	
	private void clearPlayerChat(String player) {
		for(int i = 0; i < clearSize; i++) {
			Bukkit.getServer().getPlayer(player).sendMessage("");
		}
	}
}
