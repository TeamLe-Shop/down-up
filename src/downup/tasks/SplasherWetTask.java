package downup.tasks;

import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import downup.BowInfo;
import downup.DUEffect;
import downup.DownUp;
import downup.ParticleEffect;

public class SplasherWetTask extends BukkitRunnable {
	DownUp downUp;
	Player player;
	BowInfo info;
	Arrow arrow;
	public SplasherWetTask(DownUp downUp, Player player, Arrow arrow, BowInfo info) {
		this.downUp = downUp;
		this.player = player;
		this.info = info;
		this.arrow = arrow;
	}

	public void run() {
		
		for (Entity e : downUp.getNearbyEntities(arrow.getLocation(), 3)) {
			if (e instanceof LivingEntity) {
				LivingEntity le = (LivingEntity) e;
				if (e instanceof Player) {
					Player player = (Player) e;
					if (downUp.sameTeam(player, (Player)arrow.getShooter())) {
						if (player.getFireTicks() > 0) {
							player.setFireTicks(0);
							player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 1f, 1f);
						}
						return;
					} else {
						downUp.playerResponse(player, DownUp.SOILED_WATER);
					}
				}
				if (le.equals(arrow.getMetadata("what_hit"))) {
					DUEffect.applyTo(downUp, DUEffect.WET, le, 120); // 6 seconds
					System.out.println("Soiling for longer.");
				} else {
					DUEffect.applyTo(downUp, DUEffect.WET, le, 70);  // 3.5 seconds
				}
				e.getWorld().playSound(e.getLocation(), Sound.WATER, 1f, 1f);
			}
		}
	}

}
