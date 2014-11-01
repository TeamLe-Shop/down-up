package downup.tasks;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DownUp;
import downup.cp.XYZPoint;

public class TeamRespawnTask extends BukkitRunnable {

	Player player;
	DownUp du;
	public TeamRespawnTask(DownUp du, Player player) {
		this.player = player;
		this.du = du;
	}
	
	public void run() {
		if (du.getPlayerTeam(player) == DownUp.RED_TEAM) {
			XYZPoint red = DownUp.downup.currentMap.getREDSpawn();
			player.teleport(new Location(player.getWorld(), red.x, red.y, red.z));
		} else if (du.getPlayerTeam(player) == DownUp.BLU_TEAM) {
			XYZPoint blu = DownUp.downup.currentMap.getBLUSpawn();
			player.teleport(new Location(player.getWorld(), blu.x, blu.y, blu.z));
		}
	}

}
