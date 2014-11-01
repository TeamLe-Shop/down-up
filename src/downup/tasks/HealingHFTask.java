package downup.tasks;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DownUp;
import downup.ParticleEffect;

public class HealingHFTask extends BukkitRunnable {

	DownUp downUp;
	LivingEntity entity;
	int timer;
	public HealingHFTask(DownUp downUp, LivingEntity entity, int timer) {
		this.downUp = downUp;
		this.entity = entity;
		this.timer = timer;
	}
	
	public void run() {
		if (timer == 0) {
			downUp.setMetadata(entity, "healing_hf", false);
			this.cancel();
		}
		if (timer % 2 == 0) {
			ParticleEffect.MAGIC_CRIT.display(entity.getLocation().add(0, 0.3f, 0), 0.6f, 0.2f, 0.6f, 0.1f, 23);
		}
		timer--;
	}
}
