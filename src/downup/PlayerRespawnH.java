package downup;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;

import downup.cp.XYZPoint;
import downup.tasks.TeamRespawnTask;

public class PlayerRespawnH {

	public static void handle(DownUp downUp, PlayerRespawnEvent pre) {
		Player player = pre.getPlayer();
		XYZPoint blu = DownUp.downup.currentMap.getBLUSpawn();
		XYZPoint red = DownUp.downup.currentMap.getREDSpawn();
		if (downUp.getPlayerTeam(player) == DownUp.BLU_TEAM) {
			player.teleport(new Location(player.getWorld(), blu.x, blu.y, blu.z));
			pre.setRespawnLocation(new Location(player.getWorld(), blu.x, blu.y, blu.z));
		} else if (downUp.getPlayerTeam(player) == DownUp.RED_TEAM) {
			player.teleport(new Location(player.getWorld(), red.x, red.y, red.z));
			pre.setRespawnLocation(new Location(player.getWorld(), red.x, red.y, red.z));
		}
		new TeamRespawnTask(downUp, player).runTaskLater(downUp, 2);
	}
}
