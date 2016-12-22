package cc.plus.main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class Messages extends Cooldown {
	public FileConfiguration conf = CCMain.getPlugin().getConfig();
	
	String notify = CCMain.getPlugin().getConfig().getString("Notifier").replaceAll("(&([a-f0-9]))", "\u00A7$2");
	String nc = CCMain.getPlugin().getConfig().getString("NameColor").replaceAll("(&([a-f0-9]))", "\u00A7$2");
	String mc = CCMain.getPlugin().getConfig().getString("MessageColor").replaceAll("(&([a-f0-9]))", "\u00A7$2");
	String result;
	
	public String notifyCleared(String type, String player) {
		if(type.equalsIgnoreCase("s")) {
			Bukkit.getServer().broadcastMessage(notify + " " + nc + player + mc + " cleared server chat.");
		}else{
			result = notify + " " + mc + "Your chat has been " + nc + "Cleared.";
		}
		return result;
	}
	
	public String getCooldownMessage() {
		result = notify + " " + mc + "Cooldown lasts " + nc + super.calculateTimeLeft() + mc + " more seconds.";
		return result;
	}
	
	public String getLockMessage(String player) {
		result = notify + " " + mc + "Chat was " + nc + "locked " + mc + "by " + nc + player;
		return result;
	}
	
	public String getUnlockMessage(String player) {
		result = notify + " " + mc + "Chat was " + nc + "unlocked " + mc + "by " + nc + player;
		return result;
	}
	
	public String getReloadMessage() {
		result = notify + " " + mc + "Config " + nc + "Reloaded";
		return result;
	}
	
	public String getLockOccurenceMessage() {
		result = notify + " " + mc + "Chat is " + nc + "locked " + mc + "until further notice."; 
		return result;
	}
	
	public String getDataSavedMessage(String player) {
		result = notify + " " + mc + "Data " + nc + "saved " + mc + "for " + nc + player;
		return result;
	}
	
	public String getGameModeSetMessage(String gamemode, String player) {
		result = notify + " " + mc + "Gamemode set to " + nc + gamemode + mc + " for " + nc + player;
		return result;
	}
}
