package downup;

import org.bukkit.event.player.PlayerMoveEvent;

import downup.tasks.SecondJumpMeterTask;

public class PlayerMoveH {

	public static void handle(DownUp downUp, PlayerMoveEvent pme) {
		if (pme.getFrom().distance(pme.getTo()) == 0) {
			return;
		}
		if (downUp.itemInHand(pme.getPlayer(), downUp.items.second_jmp)
				&& downUp.getMetadata(pme.getPlayer(), "sec_jmp_caninc") != Boolean.FALSE) {
			if (downUp.getMetadata(pme.getPlayer(), "sec_jmp_meter") != null) {
				Float sec_jmp_meter = (Float)downUp.getMetadata(pme.getPlayer(), "sec_jmp_meter") + (float)pme.getFrom().distance(pme.getTo());
				downUp.setMetadata(pme.getPlayer(), "sec_jmp_meter", sec_jmp_meter);
				if (sec_jmp_meter >= DownUp.MAX_SECONDJUMP_METER) {
					DUAchievements.awardAchievement(downUp, pme.getPlayer(), DUAchievements.WARMED_UP);
					downUp.playerResponse(pme.getPlayer(), DownUp.SD_HYPE_METER_FULL);
					downUp.setBoost(pme.getPlayer(), DownUp.MINICRIT);
					downUp.setMetadata(pme.getPlayer(), "sec_jmp_caninc", Boolean.FALSE);
					new SecondJumpMeterTask(downUp, pme.getPlayer()).runTaskTimer(downUp, 0, 0);
				}
				pme.getPlayer().setExp(
					((Float)downUp.getMetadata(pme.getPlayer(), "sec_jmp_meter")) / DownUp.MAX_SECONDJUMP_METER);
			} else {
				downUp.setMetadata(pme.getPlayer(), "sec_jmp_meter", 1f);
			}
		}
	}
}
