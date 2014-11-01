package downup.tasks;

import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DownUp;

public class GrillJumpingCheckTask extends BukkitRunnable {
	DownUp downUp;
	Entity entity;
	public GrillJumpingCheckTask(DownUp downUp, Entity c) {
		this.downUp = downUp;
		entity = c;
	}
	
	public void run() {
		downUp.setMetadata(entity, "grill-jumping", true);
		if (entity.isOnGround()) {
			downUp.setMetadata(entity, "grill-jumping", false);
			this.cancel();
		}
	}
}
