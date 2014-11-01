package downup.tasks;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import downup.DownUp;

public class HealBubbleTask extends BukkitRunnable {

	DownUp downUp;
	Item i;
	Entity e;
	int j;
	int timer = 25;
	
	public HealBubbleTask(DownUp downUp, Item i, Entity e, int j) {
		this.downUp = downUp;
		this.i = i;
		this.e = e;
		this.j = j;
		timer = j;
	}
	
	public void run() {
		Vector vector = e.getLocation().add(0, 1, 0).toVector().multiply(0.2).subtract(i.getLocation().toVector().multiply(0.2));
		i.setVelocity(vector.multiply(1.00f));
		timer--;
		if (i.isDead()) { i.remove(); }
		if (timer == 0) {
			i.remove();
			this.cancel();
		}
	}

}
