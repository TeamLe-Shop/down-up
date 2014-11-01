package downup.tasks;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DownUp;
import downup.cp.ControlPoint;

public class OnServerTick extends BukkitRunnable {
	DownUp downUp;
	long time = 0;
	public OnServerTick(DownUp downUp) {
		this.downUp = downUp;
	}

	public void run() {
		// Special code here !
		time++;
		// Max health, speed
		double def_health = 20;
		int boost = 0;
		int playerCount = 0;
		Player initPlayer = null;
		int cpCount = 0;
		ControlPoint initCP = null;
		float redCPProgress = 0;
		float bluCPProgress = 0;
		for (Player player : downUp.getServer().getOnlinePlayers()) {
			playerCount++;
			if (playerCount == 1) {
				initPlayer = player;
			}
			double final_health = def_health;
			if (downUp.isPlayerLLBuffed(player)) {
				final_health += 4;
			}
			if (downUp.playerHas(player, downUp.items.the_medic)) {
				final_health -= 2;
			}
			if (downUp.playerHas(player, downUp.items.return_sender)) {
				final_health += 2;
			}
			if (downUp.itemInHand(player, downUp.items.ath_assaulter)) {
				boost+=1;
			}
			if (downUp.isPlayerLLBuffed(player)) {
				boost+=2;
			}
			
			if (downUp.getMetadata(player, "stunned") == Boolean.TRUE) {
				boost-=1;
				if (downUp.getMetadata(player, "frozen") == Boolean.TRUE) {
					boost-=10;
				}
			}
			
			if (boost > 0) player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 0, boost-1));
			if (boost < 0) player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 0, (boost *= -1) - 1));
			player.setMaxHealth(final_health);

			for (ControlPoint cp : downUp.currentMap.getControlPoints()) {
				cpCount++;
				if (cpCount == 1) {
					initCP = cp;
				}
				if (player.getLocation().distance(new Location(player.getWorld(), cp.getX(), cp.getY(), cp.getZ())) < 2) {
					if (downUp.getPlayerTeam(player) == DownUp.RED_TEAM) {
						if (playerCount <= 4 && downUp.sameTeam(initPlayer, player))redCPProgress += 0.416f;;
					} else if (downUp.getPlayerTeam(player) == DownUp.BLU_TEAM) {
						if (playerCount <= 4 || downUp.sameTeam(initPlayer, player))bluCPProgress += 0.416f;;

					}
				}	
			}
			cpCount = 0;
		}
		
		
		if (bluCPProgress > 0) {
			if (redCPProgress > 0) {
				return;
			}
			boolean print = initCP.bluProgress(bluCPProgress);
		}
		if (redCPProgress > 0) {
			if (bluCPProgress > 0) {
				return;
			}
			boolean print = initCP.redProgress(redCPProgress);
			
		}

	}

}
