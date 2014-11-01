package downup.tasks;

import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DownUp;
import downup.ParticleEffect;

public class EntityBurnTask extends BukkitRunnable {
	int time = 20;
	Entity ent;
	DownUp downUp;
	public EntityBurnTask(DownUp downUp, Entity ent) {
		this.ent = ent;
		this.downUp = downUp;
	}
	
	public void run() {
		time--;
		if (time == 0) {
			this.cancel();
		}
		ParticleEffect.LARGE_SMOKE.display(ent.getLocation(), 0.2f, 0.2f, 0.2f, 0.01f, 2);
	}
}
