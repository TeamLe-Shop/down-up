package downup;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PlayerItemHeldH {

	public static void handle(DownUp downUp, PlayerItemHeldEvent pihd) {
		if (pihd.getPlayer().getInventory().getItem(pihd.getNewSlot()) == null) {
			return;
		}
		if (downUp.itemSameAs(pihd.getPlayer().getInventory().getItem(pihd.getNewSlot()), downUp.items.hungry_fighter)) {
			DUEffect.applyTo(downUp, DUEffect.HUNGER_HF, pihd.getPlayer());
		} else if (pihd.getPlayer().getInventory().getItem(pihd.getNewSlot()).equals(downUp.items.lunch_luna)) {
			Player p = pihd.getPlayer();
			if (downUp.getMetadata(p, "llrage") != null) {
				float n = (float) ((Double)downUp.getMetadata(p, "llrage") / 56);
				pihd.getPlayer().setExp(n);
			
			}
		} else if (downUp.itemSameAs(pihd.getPlayer().getInventory().getItem(pihd.getNewSlot()), downUp.items.second_jmp)) {
			Player p = pihd.getPlayer();
			if (downUp.getMetadata(p, "sec_jmp_meter") != null
					&& downUp.getMetadata(p, "sec_jmp_caninc") != Boolean.FALSE) {
				float n = (Float)downUp.getMetadata(p, "sec_jmp_meter") / DownUp.MAX_SECONDJUMP_METER;
				pihd.getPlayer().setExp(n);
			}
		}
	}

}
