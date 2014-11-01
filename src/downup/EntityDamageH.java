package downup;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import downup.tasks.EntityBurnTask;

public class EntityDamageH {

	public static void handle(DownUp downUp, EntityDamageEvent ede) {
		if (ede.getCause() == DamageCause.FIRE || ede.getCause() == DamageCause.FIRE_TICK) {
			new EntityBurnTask(downUp, ede.getEntity()).runTaskTimer(downUp, 1, 0);
		}
		if (ede.getEntity() instanceof Player) {
			Player player = (Player) ede.getEntity();
			if (downUp.playerHas(player, downUp.items.the_piercer)) {
				ede.setDamage(ede.getDamage() / 0.55);
			}
			if (downUp.getMetadata(player, "healing_hf") != null) {
				if ((Boolean)downUp.getMetadata(player, "healing_hf")) {
					double prevDmg = ede.getDamage();
					ede.setDamage(ede.getDamage() * 0.6);
					if (prevDmg >= player.getHealth() && ede.getDamage() < player.getHealth()) {
						DUAchievements.awardAchievement(downUp, player, DUAchievements.STILL_ALIVE);
					}
				}
			}
			if (downUp.playerHas(player, downUp.items.the_bakers_bow)) {
				if (ede.getCause() == DamageCause.FIRE || ede.getCause() == DamageCause.FIRE_TICK) {
					ede.setDamage(ede.getDamage() * 1.5);
				}
			}
			if (downUp.playerHas(player, downUp.items.the_splasher)) {
				if (ede.getCause() == DamageCause.PROJECTILE) {
					ede.setDamage(ede.getDamage() * 1.2);
				}
			}
			if (downUp.itemInHand(player, downUp.items.ath_assaulter)) {
				if (ede.getCause() == DamageCause.FALL) {
					if (ede.getDamage() >= 10) {
						DUAchievements.awardAchievement(downUp, player, DUAchievements.GRACEFUL_FALL);
					}
					ede.setDamage(0);
				} else {
					ede.setDamage(ede.getDamage() * 1.25);
				}
			}
			if (downUp.playerHas(player, downUp.items.aerial_swing)) {
				if (ede.getCause() == DamageCause.FIRE || ede.getCause() == DamageCause.FIRE_TICK
						|| ede.getCause() == DamageCause.LAVA) {
					ede.setDamage(ede.getDamage() / 1.6);
				} else if (ede.getCause() == DamageCause.FALL) {
					ede.setDamage(ede.getDamage() / 2);
				} else if (ede.getCause() == DamageCause.ENTITY_EXPLOSION || ede.getCause() == DamageCause.BLOCK_EXPLOSION) {
					ede.setDamage(ede.getDamage() / 0.7);
				}
			}
		}
	}
}
