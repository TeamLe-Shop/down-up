package downup;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import downup.tasks.ArrowVelocityTask;
import downup.tasks.GrillArrowTask;
import downup.tasks.SplasherArrowTask;
import downup.tasks.SulphArrowTask;

public class EntityShootBowH {
	
	public static void handle(DownUp downUp, EntityShootBowEvent esbe) {
		if (esbe.getEntity() instanceof Player) {
			Player player = (Player) esbe.getEntity();
			BowInfo bowInfo = new BowInfo(player.getInventory().getItemInHand(), esbe.getForce());
			downUp.setMetadata(esbe.getProjectile(), "bow-info", bowInfo);
			new ArrowVelocityTask(downUp, esbe.getBow(), (Arrow)esbe.getProjectile(), bowInfo).runTaskLater(downUp, 1);
			System.out.println("");
			if (downUp.itemSameAs(player.getInventory().getItemInHand(), downUp.items.the_grill)) {
				System.out.println("lil");
				if (esbe.getForce() > 0.5f) {
					new GrillArrowTask(downUp, esbe.getProjectile()).runTaskTimer(downUp, 1, 1);
					
				}
				if (esbe.getForce() > 0.6f) {
					player.getWorld().playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 100f, 10f);
				}
			} else if (downUp.itemSameAs(player.getInventory().getItemInHand(), downUp.items.the_piercer)) {
				if (!player.getInventory().containsAtLeast(new ItemStack(Material.ARROW), 6)) {
					esbe.setCancelled(true);
					player.getInventory().addItem(new ItemStack(Material.ARROW));
				} else {
					player.getInventory().removeItem(new ItemStack(Material.ARROW, 5));
					player.updateInventory();
				}
			} else if (downUp.itemSameAs(player.getInventory().getItemInHand(), downUp.items.the_sulph_shooter)) {
				new SulphArrowTask(esbe.getProjectile()).runTaskTimer(downUp, 1, 1);
			} else if (downUp.itemSameAs(player.getInventory().getItemInHand(), downUp.items.the_oxygen_blaster)) {
				for (Entity e : player.getWorld().getEntities()) {
					if (downUp.entityInRange(e, player.getLocation(), 6)) {
						downUp.pushAwayEntity(e, player.getLocation(), esbe.getForce(), 0.8f);
					}
				}
			} else if (downUp.itemSameAs(player.getInventory().getItemInHand(), downUp.items.the_splasher)) {
				if (esbe.getForce() > 0.7f) {
					new SplasherArrowTask(downUp, esbe.getProjectile()).runTaskTimer(downUp, 1, 1);
				}
			}
		}
	}
	
	
}
