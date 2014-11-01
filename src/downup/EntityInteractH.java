package downup;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import downup.tasks.DJAgainTask;
import downup.tasks.LLBuffTask;
import downup.tasks.RPGLaunchTask;
import downup.tasks.ReturnedTask;

public class EntityInteractH {

	public static void handle(DownUp downUp, PlayerInteractEvent ei) {
		Player player = ei.getPlayer();

		if (downUp.itemSameAs(player.getInventory().getItemInHand(), downUp.items.skullcutter)) {
			if (ei.getClickedBlock() != null) {
				if (ei.getClickedBlock().getType() == Material.WOODEN_DOOR ||
						ei.getClickedBlock().getType() == Material.LOG ||
						ei.getClickedBlock().getType() == Material.WOOD ||
						ei.getClickedBlock().getType() == Material.FENCE ||
						ei.getClickedBlock().getType() == Material.FENCE_GATE ||
						ei.getClickedBlock().getType() == Material.CHEST) {
					ei.getClickedBlock().setType(Material.AIR);
					player.getWorld().dropItem(ei.getClickedBlock().getLocation(), new ItemStack(Material.STICK, 2));
					player.getWorld().playSound(ei.getClickedBlock().getLocation(), Sound.ITEM_BREAK, 1F, 1F);
				}
			}
		} else if (downUp.itemSameAs(player.getInventory().getItemInHand(), downUp.items.lunch_luna)) {
			if ((Double)downUp.getMetadata(player, "llrage") >= 56) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 3));
				DUSFX.play(DUSFX.ll_call, player.getLocation(), 0);
				new LLBuffTask(downUp, player).runTaskTimer(downUp, 60, 20);
				downUp.setMetadata(player, "llrage", 0.0);
			}
		} else if (downUp.itemSameAs(player.getInventory().getItemInHand(), downUp.items.rocketLauncher)) {
			if (ei.getAction() == Action.LEFT_CLICK_AIR || ei.getAction() == Action.LEFT_CLICK_BLOCK) {
				Egg egg = player.launchProjectile(Egg.class);
				downUp.setMetadata(egg, "rpg", downUp.items.rocketLauncher);
				downUp.setMetadata(egg, "initloc", egg.getLocation());
				new RPGLaunchTask(downUp, egg).runTaskTimer(downUp, 0, 1);
				ParticleEffect.CLOUD.display(egg.getLocation(), 1f, 1f, 1f, 5f, 20);
				egg.getWorld().playSound(egg.getLocation(), Sound.FUSE, 1f, 1f);
			}
		} else if (downUp.itemSameAs(player.getInventory().getItemInHand(), downUp.items.second_jmp)) {
			System.out.println("lil-kek-lul");
			if (ei.getAction() == Action.LEFT_CLICK_BLOCK || ei.getAction() == Action.LEFT_CLICK_AIR) {
				if (downUp.playerCanDoubleJump(player)) {
					Location pushLoc = player.getLocation();
					Vector vec = player.getLocation().getDirection();
					vec.setY(0);
					pushLoc.subtract(vec);
					
					new DJAgainTask(downUp, player).runTaskTimer(downUp, 0, 0);
					downUp.pushAwayEntity(player, pushLoc, 0.55f, 0.6f);
					pushLoc.getWorld().playSound(pushLoc, Sound.GHAST_FIREBALL, 1f, 1f);
					if (downUp.getMetadata(player, "sec_jmp_active") != Boolean.TRUE) {
						player.damage(1);
					
					}
					downUp.setMetadata(player, "candoublejump", false);
				}
			}
		}
		
		if (downUp.itemInHand(player, downUp.items.return_sender)) {
			for (Entity e : downUp.getNearbyEntities(player.getLocation(), 4)) {
				if (e instanceof Projectile && !e.isOnGround()) {
					Projectile proj = (Projectile) e;
					proj.setVelocity(player.getLocation().getDirection().multiply(6f));
					proj.getWorld().playSound(proj.getLocation(), Sound.CHICKEN_EGG_POP, 1f, 1f);
					proj.getWorld().playSound(proj.getLocation(), Sound.GHAST_FIREBALL, 1f, 1f);
					downUp.setMetadata(proj, "returned", Boolean.TRUE);
					downUp.setMetadata(proj, "returned_by", player);
					new ReturnedTask(downUp, proj).runTaskTimer(downUp, 0, 0);
				}
			}
		}
		
		// DownUp snowmode
		if (downUp.itemSameAs(player.getInventory().getItemInHand(), downUp.items.snowstun) && ei.getAction() == Action.RIGHT_CLICK_AIR) {
			if (player.getInventory().containsAtLeast((new ItemStack(Material.SNOW_BALL)), 1)) {
				player.getInventory().remove(new ItemStack(Material.SNOW_BALL, 1));
			} else return;
			Snowball sb = player.launchProjectile(Snowball.class);
			downUp.setMetadata(sb, "weapon", downUp.items.snowstun);
			downUp.setMetadata(sb, "initloc", sb.getLocation());
		}
	}
}
