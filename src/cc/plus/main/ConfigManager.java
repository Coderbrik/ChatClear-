package cc.plus.main;

public class ConfigManager extends Messages {

	public void reloadConfig() {
		CCMain.getPlugin().reloadConfig();
	}

	public void saveConfig() {
		CCMain.getPlugin().saveConfig();
	}
}
