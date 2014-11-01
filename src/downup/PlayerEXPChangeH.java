package downup;

import org.bukkit.event.player.PlayerExpChangeEvent;

public class PlayerEXPChangeH {

	public static void handle(DownUp downUp, PlayerExpChangeEvent pece) {
		if (downUp.exp_enable == false) {
			pece.setAmount(0);
		}
	}
}
