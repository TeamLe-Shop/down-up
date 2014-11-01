package downup.tasks;

import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import downup.ParticleEffect;

public class SulphArrowTask extends BukkitRunnable {
	int timer = 8;
	Entity arrow;
	public SulphArrowTask(Entity entity) {
		this.arrow = entity;
	}
	
	public void run() {
		ParticleEffect.CLOUD.display(arrow.getLocation(), 0.2f, 0.2f, 0.2f, 0.2f, 2);
		timer--;
		if (timer == 0) {
			arrow.remove();
			this.cancel();
		}
	}
}
