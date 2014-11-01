package downup;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import downup.tasks.HealingHFTask;
import downup.tasks.HungerHFTask;
import downup.tasks.WetTask;

public enum DUEffect {

	/**
	 * Healing effect for 'The Hungry Fighter'
	 */
	HEALING_HF,
	/**
	 * Hunger effect for 'The Hungry Fighter'
	 */
	HUNGER_HF,
	/**
	 * Wet effect from 'The Splasher' 
	 */
	WET;

	public static void applyTo(DownUp downUp, DUEffect eff, LivingEntity player) {
		switch (eff) {
		case HEALING_HF:
			if (downUp.getMetadata(player, "healing_hf") == null) {
			} else if ((Boolean)downUp.getMetadata(player, "healing_hf")){
				break;
			}
			downUp.setMetadata(player, "healing_hf", true);
			new HealingHFTask(downUp, player, 4).runTaskTimer(downUp, 0, 10);
			break;
		case HUNGER_HF:
			if (player instanceof Player) {
				Player p = (Player) player;
				new HungerHFTask(downUp, p).runTaskTimer(downUp, 0, 20 * 6);
			}
			break;
		}
	}
	
	public static void applyTo(DownUp downUp, DUEffect eff, LivingEntity player, int data) {
		switch (eff) {
		case WET:
			downUp.setMetadata(player, "wet", Boolean.TRUE);
			new WetTask(downUp, player, data).runTaskTimer(downUp, 0, 1);
			break;
		}
	}
}
