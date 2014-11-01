package downup.tasks;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import downup.BowInfo;
import downup.DownUp;
import downup.ParticleEffect;

public class GrillFlameTask extends BukkitRunnable {
	Arrow arrow;
	DownUp downUp;
	BowInfo bowInfo;
	public GrillFlameTask(DownUp downUp, Arrow arrow, BowInfo bowInfo) {
		this.arrow = arrow;
		this.downUp = downUp;
		this.bowInfo = bowInfo;
	}
	
	public void run() {
		if (bowInfo.force == 1.0f) {
			try {
				ParticleEffect.playFirework(arrow.getWorld(), arrow.getLocation(), 
						FireworkEffect.builder().withColor(Color.ORANGE).with(Type.BALL).withFade(Color.RED).build());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (Entity c : downUp.getNearbyEntities(arrow.getLocation(), 3)) {
				if (c instanceof LivingEntity) {
					if (c instanceof Player) {
						Player play = (Player) c;
						if (!downUp.sameTeam(play, (Player)arrow.getShooter())) {
							c.setFireTicks(60);
						}
					}
					if (downUp.entityInRange(c, arrow.getLocation(), 2)) {
						if (c.equals(arrow.getShooter())) {
							if (((Player) c).isSneaking()) {
								downUp.pushAwayEntity(c, arrow.getLocation(), 1.6f, 0.35f);
								((LivingEntity) c).damage(4.0);
							} else {
								downUp.pushAwayEntity(c, arrow.getLocation(), 1.2f, 0.35f);
							}
							downUp.setMetadata(c, "grill-jumping", true);
							new GrillJumpingCheckTask(downUp, c).runTaskTimer(downUp, 1, 1);
						} else {
							if (c instanceof Player) {
								Player play = (Player) c;
								if (!downUp.sameTeam(play, (Player)arrow.getShooter())) {
									c.setFireTicks(80);
									downUp.pushAwayEntity(c, arrow.getLocation(), 1.2f, 0.35f);
								}
							} else {
								c.setFireTicks(80);
								downUp.pushAwayEntity(c, arrow.getLocation(), 1.2f, 0.35f);
							}
						}
						arrow.getWorld().playSound(arrow.getLocation(), Sound.FIREWORK_TWINKLE, 10f, 10f);
					}
				}
			}
		}
	}

}
