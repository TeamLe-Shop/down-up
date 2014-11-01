package downup.tasks;

import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import downup.ParticleEffect;

public class SkullCutterTask extends BukkitRunnable {
	Entity entity;
	int timer = 10;
	public SkullCutterTask(Entity entity) {
		this.entity = entity;
	}
	
	public void run() {
		if (timer == 0) {
			this.cancel();
			return;
		}
		ParticleEffect.RED_DUST.display(entity.getLocation(), 0.2f, 0.2f, 0.2f, 0, 5);
		entity.playEffect(EntityEffect.HURT);
		entity.getWorld().playSound(entity.getLocation(), Sound.HURT_FLESH, 1f, 1f);
		timer--;
	}

}
