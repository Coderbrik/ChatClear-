package cc.plus.playerinfo;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import cc.plus.main.CCMain;
import cc.plus.main.ConfigManager;

public class AttributesPlayer extends ConfigManager implements CommandExecutor {
	FileConfiguration conf = CCMain.getPlugin().getConfig();

	public boolean onCommand(CommandSender cs, Command cm, String alias, String[] args) {
		if(cs instanceof Player) {
			if(cm.getName().equalsIgnoreCase("ccp")) {
				if(args.length > 2) {
					if(args[0].equalsIgnoreCase("setcolor")) {
						if(!args[2].contains("&") || args[2].length() > 2) {
							cs.sendMessage("Not a valid  color code.");
						}else{
							if(Bukkit.getServer().getPlayer(args[1]) != null) {
								saveChatColor("on", args[2], args[1]);
								Config();
								
								cs.sendMessage(super.getDataSavedMessage(args[1]));
							}else{
								saveChatColor("off", args[2], args[1]);
								Config();
								
								cs.sendMessage(super.getDataSavedMessage(args[1]));
							}
						}
					}else if(args[0].equalsIgnoreCase("setprefix")) {
						if(Bukkit.getServer().getPlayer(args[1]) != null) {
							savePrefix("on", args[2], args[1]);
							Config();

							cs.sendMessage(super.getDataSavedMessage(args[1]));
						}else{
							savePrefix("off", args[2], args[1]);
							Config();

							cs.sendMessage(super.getDataSavedMessage(args[1]));
						}
					}
				}else{
					sendHelp((Player) cs);
				}
			}
		}
		return true;
	}

	private void Config() {
		super.saveConfig();
		super.reloadConfig();
	}

	private void saveChatColor(String type, String color, String name) {
		if(!type.equalsIgnoreCase("off")) {
			Player player = Bukkit.getServer().getPlayer(name);
			conf.getConfigurationSection("playerinfo").set(player.getUniqueId().toString() + ".info.chats.color", color);
		}else{
			try {
				@SuppressWarnings("deprecation")
				OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(name);
				conf.getConfigurationSection("playerinfo").set(player.getUniqueId().toString() + ".info.chats.color", color);
			}catch(Exception e) {}

		}
	}

	private void savePrefix(String type, String prefix, String name) {
		if(!type.equals("off")) {
			Player player = Bukkit.getServer().getPlayer(name);
			conf.getConfigurationSection("playerinfo").set(player.getUniqueId().toString() + ".info.chats.prefix", prefix);
		}else{
			@SuppressWarnings("deprecation")
			OfflinePlayer player = Bukkit.getOfflinePlayer(name);
			conf.getConfigurationSection("playerinfo").set(player.getUniqueId().toString() + ".info.chats.prefix", prefix);
		}
	}
	
	private void sendHelp(Player player) {
		player.sendMessage("Proper Usages:");
		player.sendMessage("/ccp setprefix <player> <prefix>");
		player.sendMessage("/ccp setcolor <player> <color>");
	}
}
