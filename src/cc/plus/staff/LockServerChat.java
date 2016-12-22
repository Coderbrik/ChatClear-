package cc.plus.staff;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import cc.plus.main.Messages;

public class LockServerChat extends Messages implements Listener, CommandExecutor {
	boolean locked;
	List<String>isAware = new ArrayList<String>();

	public boolean onCommand(CommandSender cs, Command cm, String alias, String[] args) {
		if(cs instanceof Player) {
			if(cm.getName().equalsIgnoreCase("lsc")) {
				if(locked) {
					locked = false;

					isAware.clear();
					Bukkit.getServer().broadcastMessage(super.getUnlockMessage(cs.getName()));
				}else{
					locked = true;
					lockChat(cs.getName());
				}
			}
		}
		return true;
	}

	private void lockChat(String player) {	
		Bukkit.getServer().broadcastMessage(super.getLockMessage(player));
	}
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if(locked) {
			if(!e.getPlayer().hasPermission("cc.lsc.bypass")) {
				e.setCancelled(true);

				if(!isAware.contains(e.getPlayer().getName())) {
					e.getPlayer().sendMessage(super.getLockOccurenceMessage());

					isAware.add(e.getPlayer().getName());
				}
			}
		}
	}
}
