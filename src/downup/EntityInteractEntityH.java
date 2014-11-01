package downup;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import downup.tasks.HealBubbleTask;
import downup.tasks.HealCheckTask;

public class EntityInteractEntityH {

	public static void handle(DownUp downUp, PlayerInteractEntityEvent pie) {
		Player player = pie.getPlayer();
		World world = player.getWorld();


		if (downUp.itemInHand(player, downUp.items.blitzkrieg)) {
			Item i = world.dropItem(player.getLocation().add(0, 1, 0), new ItemStack(Material.SLIME_BALL));
			Entity e = pie.getRightClicked();
			new HealBubbleTask(downUp, i, pie.getRightClicked(), 25).runTaskTimer(downUp, 0, 0);
			if (e instanceof Damageable) {
				Damageable d = (Damageable) e;
				downUp.heal(d, 2.4);
				downUp.setMetadata(player, "blitz_healing", true);
				new HealCheckTask(downUp, e, player).runTaskTimer(downUp, 0, 2);
			}
		}

		
	}
}
