package downup.tasks;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DownUp;
import downup.ParticleEffect;

public class LLBuffTask extends BukkitRunnable {
	Player player;
	DownUp downUp;
	int time = 11;
	public LLBuffTask(DownUp downUp, Player player) {
		this.player = player;
		this.downUp = downUp;
	}

	public void run() {
		int playersBuffed = 0;
		if (time == 11) {
			downUp.playerResponse(player, DownUp.LL_ACTIVATE_CALL);
			downUp.omclient.send(player.getName() + " has just activated their Lunchbox Lunatic banner!");
		}
		if (time == 0) {
			for (Entity e : player.getWorld().getEntities()) {
				if (e instanceof Player) {
					Player p = (Player) e;
					if (downUp.entityInRange(p, player.getLocation(), 14)
							&& downUp.sameTeam(player, p)) {
						downUp.setMetadata(player, "llisbuffed", (Boolean) false);
						downUp.setMetadata(player, "llrage", (Double)0.0);
						p.setMaxHealth(20);
					}
				}
			}
			this.cancel();
			return;
		}
		for (Entity e : player.getWorld().getEntities()) {
			if (e instanceof Player) {
				Player p = (Player) e;
				if (downUp.entityInRange(p, player.getLocation(), 14)
						&& downUp.sameTeam(player, p)) {
					playersBuffed++;
					downUp.setMetadata(player, "llisbuffed", (Boolean) true);
					downUp.setMetadata(player, "llrage", 0.0);
					ParticleEffect.MAGIC_CRIT.display(p.getLocation(), 1f, 1f, 1f, 0.1f, 100);
				} else {
					downUp.setMetadata(player, "llisbuffed", (Boolean) false);
				}
			}
		}
		time--;
	}

}
