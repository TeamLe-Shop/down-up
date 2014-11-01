package downup.tasks;

import org.bukkit.entity.Egg;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DownUp;
import downup.ParticleEffect;

public class RPGLaunchTask extends BukkitRunnable {

	DownUp downUp;
	Egg egg;
	int timer = 20;
	public RPGLaunchTask(DownUp downUp, Egg egg) {
		this.downUp = downUp;
		this.egg = egg;
	}
	
	public void run() {
		if (timer == 0) {
			this.cancel();
		}
		timer--;
		ParticleEffect.CLOUD.display(egg.getLocation(), 0.1f, 0.1f, 0.1f, 0.1f, 3);
	}
}
