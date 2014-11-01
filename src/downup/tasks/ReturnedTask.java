package downup.tasks;

import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DownUp;
import downup.ParticleEffect;

public class ReturnedTask extends BukkitRunnable {
	int sparktime = 40;
	Entity arrow;
	DownUp downUp;
	public ReturnedTask(DownUp downUp, Entity entity) {
		this.arrow = entity;
		this.downUp = downUp;
	}
	
	public void run() {
		
		if (sparktime == 0) {
			this.cancel();
		} else {
			if (downUp.getMetadata(arrow, "phf") != null) {
				this.cancel();
			}
			ParticleEffect.MAGIC_CRIT.display(arrow.getLocation(), 0.01f, 0.02f, 0.01f, 0.2f, 8);
			sparktime--;
		}
	}
}
