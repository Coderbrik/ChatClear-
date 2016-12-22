package cc.plus.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import cc.plus.chathandlers.ChatEventHandler;
import cc.plus.chathandlers.DeathEventHandler;
import cc.plus.chathandlers.JoinMessageHandler;
import cc.plus.chathandlers.LeaveEventHandler;
import cc.plus.command.PlayerClearChat;
import cc.plus.command.PlayerGamemodes;
import cc.plus.playerinfo.AttributesPlayer;
import cc.plus.staff.LockServerChat;
import cc.plus.staff.ReloadConfig;
import cc.plus.staff.ServerClearChat;

public class CCMain extends JavaPlugin {
	
	private static CCMain plugin;

	public void onEnable() {
		CCMain.plugin = this;
		
		if(!new File(getDataFolder(), "config.yml").exists()) {
			saveDefaultConfig();
		}
		isSectionThere("playerinfo");
		registerInstances();
		registerCommands();
		registerEvents();
	}

	public void onDisable() {}
	
	private void registerCommands() {
		getCommand("pcc").setExecutor(new PlayerClearChat());
		getCommand("scc").setExecutor(new ServerClearChat());
		getCommand("ccpreload").setExecutor(new ReloadConfig());
		getCommand("ccp").setExecutor(new AttributesPlayer());
		
		PlayerGamemodes gm = new PlayerGamemodes();
		getCommand("gmc").setExecutor(gm);
		getCommand("gms").setExecutor(gm);
		getCommand("gma").setExecutor(gm);
		getCommand("gmsp").setExecutor(gm);
	}
	
	private void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new ChatEventHandler(), this);
		Bukkit.getPluginManager().registerEvents(new JoinMessageHandler(), this);
		Bukkit.getPluginManager().registerEvents(new LeaveEventHandler(), this);
		Bukkit.getPluginManager().registerEvents(new DeathEventHandler(), this);
	}
	
	private void registerInstances() {
		LockServerChat lock = new LockServerChat();
		getCommand("lsc").setExecutor(lock);
		Bukkit.getServer().getPluginManager().registerEvents(lock, this);
	}
	
	private void isSectionThere(String section) {
		if(CCMain.getPlugin().getConfig().getConfigurationSection("playerinfo") == null) {
			createConfigurationSection(section);
			}
	}
	
	private void createConfigurationSection(String section) {
		CCMain.getPlugin().getConfig().createSection(section);
	}
	
	public static CCMain getPlugin() {	return plugin; }

	public static void setPlugin(CCMain plugin) {
		CCMain.plugin = plugin;
	}
}
