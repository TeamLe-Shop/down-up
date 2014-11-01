package downup.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DownUp;

public class SkullCutterAchTask extends BukkitRunnable {

	DownUp downUp;
	Player player;
	int timer = 16;
	public SkullCutterAchTask(DownUp downUp, Player player) {
		this.downUp = downUp;
		this.player = player;
	}
	
	public void run() {
		if (timer == 0) {
			downUp.setMetadata(player, "skullcutter-ach-kills", 0);
		}
		timer--;
	}
}
