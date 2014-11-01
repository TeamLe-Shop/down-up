package downup.tasks;

import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DownUp;
import downup.ParticleEffect;

public class SplasherArrowTask extends BukkitRunnable {

	DownUp downUp;
	Entity arrow;
	int timer = 40;
	public SplasherArrowTask(DownUp downUp, Entity arrow) {
		this.downUp = downUp;
		this.arrow = arrow;
	}
	
	public void run() {
		timer--;
		ParticleEffect.SPLASH.display(arrow.getLocation(), 0.5f, 0.5f, 0.6f, 0.01f, 10);
		if (timer == 0) {
			this.cancel();
			return;
		}
	}

}
