package downup.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DownUp;
import downup.ParticleEffect;

public class DJAgainTask extends BukkitRunnable {
	int djsmoke = 10;
	Player player;
	DownUp downUp;
	public DJAgainTask(DownUp downUp, Player player) {
		this.downUp = downUp;
		this.player = player;
	}
	
	public void run() {
		if (djsmoke > 0) {
			if (downUp.getMetadata(player, "sec_jmp_active") == Boolean.TRUE) {
				ParticleEffect.RED_DUST.display(player.getLocation(), 0.06f, 0.06f, 0.06f, 0f, 10);
			} else {
				ParticleEffect.CLOUD.display(player.getLocation(), 0.01f, 0.01f, 0.01f, 0.1f, 5);
			}
			djsmoke--;
		}
		if (player.isOnGround()) {
			downUp.setMetadata(player, "candoublejump", Boolean.TRUE);
			this.cancel();
		}
	}
}
