package downup;

import org.bukkit.inventory.ItemStack;


public class BowInfo {
	public ItemStack bow;
	public float force;
	public BowInfo(ItemStack bow, float force) {
		this.bow = bow;
		this.force = force;
	}

}
