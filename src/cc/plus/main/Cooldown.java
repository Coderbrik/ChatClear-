package cc.plus.main;

import org.bukkit.configuration.file.FileConfiguration;

public class Cooldown {
	FileConfiguration conf = CCMain.getPlugin().getConfig();
	public int cooldownTime = conf.getInt("Cooldown Time");

	public long lastSuccessfulRun;
    int left;

	public boolean canRun;
	long timeLeft;

	public int calculateTimeLeft() {
		long timeinSeconds = System.currentTimeMillis() - lastSuccessfulRun;

		if(timeinSeconds / 1000 >= cooldownTime) {
			canRun = true;
		}else{
			canRun = false;
			
			timeLeft = timeinSeconds / 1000;
			timeLeft = cooldownTime - timeLeft;
		}
		return (int) timeLeft;
	}

}
