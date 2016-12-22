package cc.plus.command;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.plus.main.Messages;

public class PlayerGamemodes extends Messages implements CommandExecutor {
	Player player;

	public boolean onCommand(CommandSender cs, Command cm, String alias, String[] args) {
		if(cs instanceof Player) {
			setPlayer((Player) cs);
			if(cm.getName().equalsIgnoreCase("gmc")) {
				if(args.length == 0) {
					if(Bukkit.getServer().getPlayer(args[0]) != null) {
						Bukkit.getServer().getPlayer(args[0]).setGameMode(GameMode.CREATIVE);
						player.sendMessage(super.getGameModeSetMessage("Creative", args[0]));
					}
				}else{
					((Player) cs).setGameMode(GameMode.CREATIVE);
					player.sendMessage(super.getGameModeSetMessage("Creative", player.getName()));
				}
			}else if(cm.getName().equalsIgnoreCase("gms")) {
				if(args.length == 0) {
					if(Bukkit.getServer().getPlayer(args[0]) != null) {
						Bukkit.getServer().getPlayer(args[0]).setGameMode(GameMode.SURVIVAL);
						player.sendMessage(super.getGameModeSetMessage("Surival", args[0]));
					}
				}else{
					player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage(super.getGameModeSetMessage("Survival", player.getName()));
				}
			}else if(cm.getName().equalsIgnoreCase("gmsp")) {
				player.setGameMode(GameMode.SPECTATOR);
				player.sendMessage(super.getGameModeSetMessage("Spectator", player.getName()));
			}else if(cm.getName().equalsIgnoreCase("gma")) {
				player.setGameMode(GameMode.ADVENTURE);
				player.sendMessage(super.getGameModeSetMessage("Adventure", player.getName()));
			}
		}
		return true;
	}

	private void setPlayer(Player player) {
		this.player = player;
	}
}
