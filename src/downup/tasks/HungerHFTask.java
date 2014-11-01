package downup.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DownUp;

public class HungerHFTask extends BukkitRunnable {
	DownUp downUp;
	Player player;
	
	public HungerHFTask(DownUp downUp, Player player) {
		this.downUp = downUp;
		this.player = player;
	}

	public void run() {
		player.setFoodLevel(player.getFoodLevel() - 1);
		if (!downUp.itemSameAs(player.getItemInHand(), downUp.items.hungry_fighter)) {
			this.cancel();
		}
	}

}
