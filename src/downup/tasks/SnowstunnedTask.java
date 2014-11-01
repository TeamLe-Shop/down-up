package downup.tasks;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DownUp;

public class SnowstunnedTask extends BukkitRunnable {

	LivingEntity ent;
	int stunTicks;
	DownUp downUp;
	int timer;

	public SnowstunnedTask(DownUp downUp, int stunTicks, LivingEntity ent) {
		this.downUp = downUp;
		this.timer = stunTicks;
		this.stunTicks = stunTicks;
		this.ent = ent;
	}

	public void run() {
		downUp.setMetadata(ent, "stunned", true);
		if (!(ent instanceof Player)) {
			ent.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 0, 6));
		}
		System.out.println("fg");
		if (stunTicks > 100) {
			downUp.setMetadata(ent, "frozen", true);
			if (!(ent instanceof Player)) {
				ent.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 0, 10));
			}
		}
		
		timer--;
		if (timer < 0) {
			this.cancel();
			downUp.setMetadata(ent, "stunned", false);
			downUp.setMetadata(ent, "frozen", false);
		}
	}

}
