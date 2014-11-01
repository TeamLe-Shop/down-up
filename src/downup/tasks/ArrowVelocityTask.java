package downup.tasks;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import downup.BowInfo;
import downup.DownUp;

public class ArrowVelocityTask extends BukkitRunnable {
	
	Arrow arrow;
	ItemStack bow;
	DownUp downUp;
	BowInfo info;
	public ArrowVelocityTask(DownUp downUp, ItemStack bow, Arrow arrow, BowInfo info) {
		this.downUp = downUp;
		this.arrow = arrow;
		this.bow = bow;
		this.info = info;
	}
	
	public void run() {
		if (downUp.itemSameAs(bow, downUp.items.the_grill)) {
			arrow.setVelocity(arrow.getVelocity().multiply(1.4));
		} else if (downUp.itemSameAs(bow, downUp.items.the_sulph_shooter)) {
			arrow.setVelocity(arrow.getVelocity().multiply(0.85));
		} else if (downUp.itemSameAs(bow, downUp.items.second_jmp)) {
			deviationVec(arrow, 35, info.force);
		}
	}
	
	public static double calcDeviation(double percent) {
		return ((Math.random() * 2) - 1) * (percent / 100d);
	}
	
	public static void deviationVec(Entity pj, int perc, float force) {
		pj.setVelocity(pj.getVelocity().add(new Vector(calcDeviation(perc)*force, calcDeviation(perc)*force, calcDeviation(perc)*force)));
	}
}
