package downup.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DownUp;
import downup.EntityDamageByEntityH;

public class SecondJumpMeterTask extends BukkitRunnable {
	
	DownUp downUp;
	Player player;
	float max = 8 * 20;
	float time = max;
	public SecondJumpMeterTask(DownUp downUp, Player player) {
		this.downUp = downUp;
		this.player = player;
	}
	
	public void run() {
		downUp.setMetadata(player, "sec_jmp_meter", 0f);
		player.setExp(time / max);
		downUp.setMetadata(player, "sec_jmp_active", Boolean.TRUE);
		time--;
		if (time == 0) {
			downUp.setMetadata(player, "sec_jmp_caninc", Boolean.TRUE);
			downUp.setMetadata(player, "sec_jmp_active", Boolean.FALSE);
			downUp.setBoost(player, DownUp.NOBOOST);
			downUp.setMetadata(player, "sec_jmp_meter", 0f);
			this.cancel();
			return;
		}
	}
}
