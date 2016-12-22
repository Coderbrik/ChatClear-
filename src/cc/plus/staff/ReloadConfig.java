package cc.plus.staff;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.plus.main.ConfigManager;

public class ReloadConfig extends ConfigManager implements CommandExecutor {
	
	public boolean onCommand(CommandSender cs, Command cm, String alias, String[] args) {
		if(cs instanceof Player) {
			if(cm.getName().equalsIgnoreCase("ccpreload")) {
				try {
					super.reloadConfig();
					super.saveConfig();
					super.reloadConfig();
					cs.sendMessage(super.getReloadMessage());
				}catch(Exception e) { e.printStackTrace(); }
			}
		}
		return true;
	}
	
	

}
