package downup.tasks;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DownUp;
import downup.ParticleEffect;

public class WetTask extends BukkitRunnable {

	LivingEntity e;
	DownUp downUp;
	int timer = 0;
	public WetTask(DownUp downUp, LivingEntity e, int time) {
		this.e = e;
		this.downUp = downUp;
		timer = time;
	}
	
	public void run() {
		ParticleEffect.SPLASH.display(e.getLocation(), 1f, 1f, 1f, 0.005f, 10);
		timer--;
		if (timer == 0 || e.isDead()) {
			downUp.setMetadata(e, "wet", Boolean.FALSE);
			this.cancel();
			return;
		}
	}

}
