package downup;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import downup.tasks.GrillFlameTask;
import downup.tasks.SplasherWetTask;

public class ProjectileHitH {

	public static void handle(DownUp downUp, ProjectileHitEvent phe) {
		if (phe.getEntity() instanceof Arrow) {
			Arrow arrow = (Arrow) phe.getEntity();
			downUp.setMetadata(arrow, "phf", true);
			BowInfo info = (BowInfo) downUp.getMetadata(arrow, "bow-info");
			if (info != null) {
				if (downUp.itemSameAs(info.bow, downUp.items.the_grill)) {
					new GrillFlameTask(downUp, arrow, info).runTaskLater(downUp, 1);
				} else if (downUp.itemSameAs(info.bow, downUp.items.the_splasher)) {
					if (info.force == 1.0f) {
						new SplasherWetTask(downUp, (Player)arrow.getShooter(), arrow, info).runTaskLater(downUp, 1);
					}
				} else {
					return;
				}
			}
		} else if (phe.getEntity() instanceof Egg) {
			Egg egg = (Egg) phe.getEntity();
			ParticleEffect.EXPLODE.display(egg.getLocation(), 0, 0, 0, 0.1f, 1);
			egg.getWorld().playSound(egg.getLocation(), Sound.EXPLODE, 1f, 1f);
			if (downUp.itemSameAs((ItemStack)downUp.getMetadata(egg, "rpg"), downUp.items.rocketLauncher)) {
				double minRamp = 0.45;
				double maxRamp = -0.15;
				for (Entity e : downUp.getNearbyEntities(egg.getLocation(), 4)) {
					//Calculate regular damage without splash falloff
					double dist = downUp.getDist(e.getLocation(), (Location)downUp.getMetadata(egg,"initloc"));
					double normalDMG = 9;
					double avgDist = 7;
					double ramp = dist / avgDist;
					double finalRMP = ramp - 1;
					
					if (finalRMP > minRamp) {
						finalRMP = minRamp;
					}
					if (finalRMP < maxRamp) {
						finalRMP = maxRamp;
					}
					//Then calculate splash damage
					double distForSplash = downUp.getDist(egg.getLocation(), e.getLocation());
					double avgSplashDist = 2;
					double splashDMGMod = avgSplashDist / distForSplash;
					if (splashDMGMod > 2) {
						splashDMGMod = 2;
					}
					double decidedDMG = (normalDMG-(normalDMG*finalRMP)) * (splashDMGMod/2);
					if (e instanceof Damageable) {
						Damageable de = (Damageable) e;
						de.damage(decidedDMG);
						downUp.pushAwayEntity(de, egg.getLocation(), (decidedDMG / 9) * 0.7, 0.1f);
					}
				}
			}
			
		} else if (phe.getEntity() instanceof Snowball) {
			Snowball sb = (Snowball) phe.getEntity();
			if (downUp.getMetadata(sb, "weapon").equals(downUp.items.snowstun)) {
				
			}
		}
	}
}
