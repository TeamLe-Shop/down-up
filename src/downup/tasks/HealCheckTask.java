package downup.tasks;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DownUp;

public class HealCheckTask extends BukkitRunnable {
	
	DownUp downUp;
	Entity ent;
	Player player;
	
	public HealCheckTask(DownUp downUp, Entity ent, Player player) {
		this.downUp = downUp;
		this.ent = ent;
		this.player = player;
	}
	
	public void run() {
		if (downUp.getMetadata(player, "blitz_healing").equals(Boolean.TRUE)) {
			return;
		} else {
			this.cancel();
		}
	}

}
