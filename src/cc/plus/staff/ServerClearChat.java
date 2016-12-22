package cc.plus.staff;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.plus.main.CCMain;
import cc.plus.main.ConfigManager;

public class ServerClearChat extends ConfigManager implements CommandExecutor {
	int clearSize = CCMain.getPlugin().getConfig().getInt("ClearAmount");

	public boolean onCommand(CommandSender cs, Command cm, String alias, String[] args) {
		if(cs instanceof Player) {
			if(!(super.cooldownTime < 1)) {
				if(cm.getName().equalsIgnoreCase("scc")) {
					if(super.canRun) {
						cs.sendMessage("Test");
						super.canRun = false;
						super.lastSuccessfulRun = System.currentTimeMillis();

						clearServerChat();
						super.notifyCleared("s", cs.getName());
					}else{
						cs.sendMessage(super.getCooldownMessage());	
					}
				}
			}else{
				clearServerChat();
				super.notifyCleared("s", cs.getName());
			}
		}
		return true;
	}

	private void clearServerChat() {
		for(int i = 0; i < clearSize; i++) {
			Bukkit.getServer().broadcastMessage("");
		}
	}

}
