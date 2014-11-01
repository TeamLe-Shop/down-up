package downup;

import java.util.Random;

import org.bukkit.Sound;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;

import downup.tasks.SkullCutterAchTask;

public class EntityDeathH {
	static Random rand = new Random();
	public static void handle(DownUp downUp, EntityDeathEvent esbe) {
		if (esbe.getEntity().getKiller() instanceof Projectile) {
			Projectile p = (Projectile) esbe.getEntity().getKiller();
			if (downUp.getMetadata(p, "returned") == Boolean.TRUE) {
				Player returner = (Player) downUp.getMetadata(p, "returned_by");
				DUAchievements.awardAchievement(downUp, returner, DUAchievements.ADDRESS_UNKNOWN);
			}
		}
		
		if (esbe.getEntity().getKiller() instanceof Player) {
			Player player = (Player) esbe.getEntity().getKiller();
			if (downUp.itemSameAs(player.getInventory().getItemInHand(), downUp.items.skullcutter)) {
				if (downUp.getMetadata(player, "skullcutter-ach-kills") != null) {
					if (downUp.getMetadata(player, "skullcutter-ach-kills") == (Integer)0) {
						new SkullCutterAchTask(downUp, player).runTaskTimer(downUp, 0, 20);

					}
					downUp.setMetadata(player, "skullcutter-ach-kills",
							(Integer)downUp.getMetadata(player, "skullcutter-ach-kills") + 1);
					if (downUp.getMetadata(player, "skullcutter-ach-kills") == (Integer)3) {
						DUAchievements.awardAchievement(downUp, player, DUAchievements.QUICK_CHOPS);
					}
				} else {
					downUp.setMetadata(player, "skullcutter-ach-kills",
							1);
					new SkullCutterAchTask(downUp, player).runTaskTimer(downUp, 0, 20);
				}
			}
			if (downUp.getMetadata(player, "sec_jmp-ach-kills") != null
					&& downUp.getMetadata(player, "sec_jmp_active") == Boolean.TRUE) {
				downUp.setMetadata(player, "sec_jmp-ach-kills",
						(Integer)downUp.getMetadata(player, "sec_jmp-ach-kills") + 1);
				if (downUp.getMetadata(player, "sec_jmp-ach-kills") == (Integer)4) {
					DUAchievements.awardAchievement(downUp, player, DUAchievements.GET_HYPER);
				}
			} else {
				downUp.setMetadata(player, "sec_jmp-ach-kills", 1);
			}
			if (downUp.itemSameAs(player.getInventory().getItemInHand(), downUp.items.sodium_slicer)) {
				if (esbe.getEntity().getLastDamageCause().getCause() == DamageCause.ENTITY_ATTACK) {
					ParticleEffect.LARGE_EXPLODE.display(esbe.getEntity().getLocation(), 0f, 0f, 0f, 0, 1);
					esbe.getEntity().getWorld().playSound(esbe.getEntity().getLocation(), Sound.EXPLODE, 1.0f, 1.0f);
				}
			}
		}

		if (esbe.getEntity() instanceof Player) {
			Player player = (Player) esbe.getEntity();
			downUp.setMetadata(player, "llrage", (double)0);
			downUp.setMetadata(player, "sec_jmp_meter", (float)0);
			downUp.omclient.send(player.getName() + " died.");
		}

		if (downUp.mob_drops) {
			if (esbe.getEntity() instanceof Creeper) {

				Creeper creeper = (Creeper) esbe.getEntity();
				if (creeper.getKiller() instanceof Player) {
					Player killer = creeper.getKiller();
					killer.getInventory().addItem(downUp.items.the_grill);
				}
			} else if (esbe.getEntity() instanceof Zombie) {
				Zombie zomb = (Zombie) esbe.getEntity();
				if (zomb.getKiller() instanceof Player) {
					Player killer = zomb.getKiller();
					killer.getInventory().addItem(downUp.items.the_bakers_bow);
				}
			} else if (esbe.getEntity() instanceof Skeleton) {
				Skeleton zomb = (Skeleton) esbe.getEntity();
				if (zomb.getKiller() instanceof Player) {
					Player killer = zomb.getKiller();
					killer.getInventory().addItem(downUp.items.hungry_fighter);
				}
			} else if (esbe.getEntity() instanceof Spider) {
				Spider zomb = (Spider) esbe.getEntity();
				if (zomb.getKiller() instanceof Player) {
					Player killer = zomb.getKiller();
					killer.getInventory().addItem(downUp.items.the_piercer);
				}
			} else if (esbe.getEntity() instanceof Pig) {
				Pig zomb = (Pig) esbe.getEntity();
				if (zomb.getKiller() instanceof Player) {
					Player killer = zomb.getKiller();
					killer.getInventory().addItem(downUp.items.skullcutter);
				}
			} else if (esbe.getEntity() instanceof Snowman) {
				Snowman zomb = (Snowman) esbe.getEntity();
				if (zomb.getKiller() instanceof Player) {
					Player killer = zomb.getKiller();
					killer.getInventory().addItem(downUp.items.aerial_swing);
					killer.getInventory().addItem(downUp.items.lunch_luna);
				}
			} else if (esbe.getEntity() instanceof Bat) {
				Bat zomb = (Bat) esbe.getEntity();
				if (zomb.getKiller() instanceof Player) {
					if (rand.nextInt(10) == 0) {
						Player killer = zomb.getKiller();
						killer.getInventory().addItem(downUp.items.sodium_slicer);
					}
				}
			} else if (esbe.getEntity() instanceof Witch) {
				Witch zomb = (Witch) esbe.getEntity();
				if (zomb.getKiller() instanceof Player) {
					if (rand.nextInt(8) == 0) {
						Player killer = zomb.getKiller();
						killer.getInventory().addItem(downUp.items.the_sulph_shooter);
					} else if (rand.nextInt(5) == 0) {
						Player killer = zomb.getKiller();
						killer.getInventory().addItem(downUp.items.the_oxygen_blaster);
					}
				}
			}
		}
	}
}
