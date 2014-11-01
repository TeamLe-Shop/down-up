package downup;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import downup.tasks.SkullCutterTask;
import downup.tasks.SnowstunnedTask;

public class EntityDamageByEntityH {

	public static final int NO_CRIT = 0, MINI_CRIT = 1, CRIT = 2;
	public static void handle(DownUp downUp, EntityDamageByEntityEvent ede) {
		int crit = 0;
		if (ede.getEntity() instanceof Player) {
			Player player = (Player) ede.getEntity();
			if (ede.getDamager() instanceof Player) {
				Player dmger = (Player) ede.getDamager();

				if (downUp.sameTeam(player, dmger)) {
					ede.setCancelled(true);
					return;
				}
			} else if (ede.getDamager() instanceof Arrow) {

				if (((Arrow)ede.getDamager()).getShooter().equals(player)) {
					Projectile p = (Arrow) ede.getDamager();
					Player player2 = (Player) ((Arrow)ede.getDamager()).getShooter();
					if (downUp.sameTeam(player2, player)) {
						ede.setCancelled(true);
						//Special code!!
						BowInfo info = (BowInfo)downUp.getMetadata(p, "bow-info");
						if (downUp.itemSameAs(info.bow, downUp.items.the_medic)) {
							downUp.heal(player2, Math.round(5 * info.force * downUp.calcRamp(
									(float)downUp.getDist(player2.getLocation(), player.getLocation()), 7f, 1f, 2f)));
							ParticleEffect.HEART.display(player2.getLocation().add(0, 2, 0), 0f, 0.1f, 0f, 0.02f, 1);
							player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
						}
					}
				}
			}
			if (downUp.playerHas(player, downUp.items.the_oxygen_blaster)) {
				if (player.getFireTicks() > 10) {
					crit = MINI_CRIT;
				}
			}
		}


		if (ede.getDamager() instanceof Player) {
			Player player = (Player) ede.getDamager();
			if (downUp.getBoost(player) == DownUp.MINICRIT) {
				crit = MINI_CRIT;
			}
			if (downUp.itemSameAs(player.getInventory().getItemInHand(), downUp.items.hungry_fighter)) {
				DUEffect.applyTo(downUp, DUEffect.HEALING_HF, player);
			} else if (downUp.itemSameAs(player.getInventory().getItemInHand(), downUp.items.skullcutter)) {
				downUp.pushAwayEntity(ede.getEntity(), ede.getDamager().getLocation().subtract(0, 0.5, 0), 0.7, 0.9);
				new SkullCutterTask(ede.getEntity()).runTaskTimer(downUp, 1, 2);
			} else if (downUp.itemSameAs(player.getInventory().getItemInHand(), downUp.items.aerial_swing)) {
				if (downUp.getMetadata(player, "grill-jumping") != null) {
					if (downUp.getMetadata(player, "grill-jumping") == (Boolean)false) {
						ede.setDamage(ede.getDamage() * 0.5);
					} else {
						DUAchievements.awardAchievement(downUp, player, DUAchievements.AERIAL_CRIT);
						player.sendMessage(ChatColor.RED + "Critswing!");
						ede.setDamage(40);
						player.setVelocity(player.getVelocity().add(new Vector(0, 2.4f, 0)));
						player.getWorld().playSound(ede.getEntity().getLocation(), Sound.ANVIL_LAND, 3f, 100f);
						downUp.heal(player, 10);
					}
				} else {
					ede.setDamage(ede.getDamage() * 0.5);
				}
			} else if (downUp.itemSameAs(player.getInventory().getItemInHand(), downUp.items.sodium_slicer)) {
				if (ede.getEntity().getLocation().getBlock().getType() == Material.STATIONARY_WATER
						|| ede.getEntity().getLocation().getBlock().getType() == Material.WATER
						|| downUp.getMetadata(ede.getEntity(), "wet") == Boolean.TRUE) {
					crit = CRIT;
					ede.getEntity().setVelocity(ede.getEntity().getVelocity().add(new Vector(0f, 1.2f, 0f)));
					ParticleEffect.MAGIC_CRIT.display(ede.getEntity().getLocation(), 1.5f, 1.5f, 1.5f, 0.2f, 50);
				} else {
					ede.setDamage(ede.getDamage() * 0.75);
				}
			}
		} else if (ede.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) ede.getDamager();
			downUp.setMetadata(arrow, "edef", true);
			downUp.setMetadata(arrow, "what_hit", ede.getEntity());
			System.out.println("dmged by arrow.");
			if (downUp.getMetadata(ede.getDamager(), "returned") == Boolean.TRUE) {
				System.out.println("returned to sender. :)");
				Player returner = (Player)downUp.getMetadata(ede.getDamager(), "returned_by");
				returner.sendMessage("Damage: " + Math.round(ede.getDamage()));
				if (ede.getEntity() instanceof Player) {
					String[] return_lines = {"Address unknown.", "No such number.", "No such zone.", "No such person."};
					Random r = new Random();
					((Player) ede.getEntity()).sendMessage(ChatColor.GOLD + return_lines[r.nextInt(return_lines.length)]);
				}
			}
			if (arrow.getShooter() instanceof Player) {
				Player player = (Player) arrow.getShooter();
				if (downUp.getBoost(player) == DownUp.MINICRIT) {
					crit = MINI_CRIT;
				}
				BowInfo bowInfo = (BowInfo) downUp.getMetadata(arrow, "bow-info");
				if (downUp.itemSameAs(bowInfo.bow, downUp.items.the_grill)) {
					ede.setDamage(ede.getDamage() * 0.35);
				} else if (downUp.itemSameAs(bowInfo.bow, downUp.items.the_piercer)) {
					ede.setDamage(ede.getDamage() * 1.5);
					if (!ede.getEntity().isOnGround()) {
						ede.setDamage(ede.getDamage() * 1.2);
					}
				} else if (downUp.itemSameAs(bowInfo.bow, downUp.items.the_sulph_shooter)) {
					ede.setDamage(ede.getDamage() * 1.2);
					if (ede.getEntity() instanceof Creeper || ede.getEntity().getFireTicks() > 5) {
						crit = MINI_CRIT;
					}
				} else if (downUp.itemSameAs(bowInfo.bow, downUp.items.the_splasher)) {
					if (bowInfo.force < 1.0f) {
						ede.setDamage(ede.getDamage() * 0.85);
					}
				} else if (downUp.itemSameAs(bowInfo.bow, downUp.items.the_oxygen_blaster)) {
					if (ede.getEntity().isOnGround()) {
						ede.setDamage(ede.getDamage() * 0.8);
					}
					if (!ede.getEntity().isOnGround()) {
						downUp.pushAwayEntity(ede.getEntity(), arrow.getLocation().subtract(0, 0.4f, 0), 1f, 0f);
					}
					if (ede.getEntity().getFireTicks() > 0) {
						ede.getEntity().setFireTicks(ede.getEntity().getFireTicks() + 60);
						ParticleEffect.FLAME.display(ede.getEntity().getLocation(), 1.5f, 1.5f, 1.5f, 0.1f, 100);
					}
					int ox_bl_radius = 6;
					for (int x = 0; x < ox_bl_radius; x++) {
						for (int y = 0; y < ox_bl_radius; y++) { 
							for (int z = 0; z < ox_bl_radius; z++) {
								World world = ede.getEntity().getWorld();
								if (world.getBlockAt(x, y, z).getType() == Material.FIRE) {
									world.getBlockAt(x, y, z).setType(Material.AIR);
								}
								Fireball fireball = world.spawn(world.getBlockAt(x, y, z).getLocation(), Fireball.class);
								fireball.setShooter(player);
								fireball.setDirection(new Vector());
							}
						}
					}
				} 
				if (ede.getEntity() instanceof Damageable) {
					Damageable le = (Damageable) ede.getEntity();
					if (ede.getDamage() >= le.getHealth()) {
						if (downUp.itemSameAs(bowInfo.bow, downUp.items.the_bakers_bow)) {
							player.getInventory().addItem(new ItemStack(Material.BREAD));
						}
					}
					if (downUp.itemSameAs(bowInfo.bow, downUp.items.the_piercer) && bowInfo.force == 1.0f) {
						if (le instanceof Player ||
								le instanceof Skeleton ||
								le instanceof Creeper ||
								le instanceof Zombie ||
								le instanceof PigZombie) {

							Arrow newArrow = arrow.getWorld().spawnArrow(
									arrow.getLocation().add(arrow.getVelocity().getX(), arrow.getVelocity().getY(), arrow.getVelocity().getZ())
									, arrow.getVelocity().multiply(3), 2f, 1);
							newArrow.setShooter(player);
							downUp.setMetadata(newArrow, "bow-info", downUp.getMetadata(arrow, "bow-info"));
							if (downUp.getMetadata(arrow, "piercer-mobcount") != null) {
								downUp.setMetadata(newArrow, "piercer-mobcount", (Integer)downUp.getMetadata(arrow, "piercer-mobcount") + 1);
								if (downUp.getMetadata(newArrow, "piercer-mobcount") == (Integer)4) {
									DUAchievements.awardAchievement(downUp, player, DUAchievements.MOB_KEBAB);
								}
							} else {
								downUp.setMetadata(newArrow, "piercer-mobcount", 1);
							}
						}
					}
				}
			}
		}
		if (crit == CRIT) {
			ede.setDamage(ede.getDamage() * 3);
			ede.getEntity().getWorld().playSound(ede.getEntity().getLocation(), Sound.ANVIL_LAND, 1f, 10f);
		} else if (crit == MINI_CRIT) {
			ede.setDamage(ede.getDamage() * 1.35);
			ede.getEntity().getWorld().playSound(ede.getEntity().getLocation(), Sound.ANVIL_LAND, 1f, 20f);
		}
		if (ede.getDamager() instanceof Player) {
			Player player = (Player) ede.getDamager();
			if (crit == CRIT) {
				player.sendMessage(ChatColor.RED + "Damage: " + Math.round(ede.getDamage()));
			} else if (crit == MINI_CRIT) {
				player.sendMessage(ChatColor.GOLD + "Damage: " + Math.round(ede.getDamage()));
			} else {
				player.sendMessage("Damage: " + Math.round(ede.getDamage()));
			}
			if ((Boolean)downUp.getMetadata(player, "llisbuffed") != null) {
				if ((Boolean)downUp.getMetadata(player, "llisbuffed")) {
					downUp.heal(player, (int)Math.round(ede.getDamage() * 0.4));
				}
			}
		} else if (ede.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) ede.getDamager();
			if (arrow.getShooter() instanceof Player) {
				Player player = (Player) arrow.getShooter();
				if (crit == CRIT) {
					player.sendMessage(ChatColor.RED + "Damage: " + Math.round(ede.getDamage()));
				} else if (crit == MINI_CRIT) {
					player.sendMessage(ChatColor.GOLD + "Damage: " + Math.round(ede.getDamage()));
				} else {
					player.sendMessage("Damage: " + Math.round(ede.getDamage()));
				}
				if ((Boolean)downUp.getMetadata(player, "llisbuffed") != null) {
					if ((Boolean)downUp.getMetadata(player, "llisbuffed")) {
						downUp.heal(player, (int)Math.round(ede.getDamage() * 0.4));
					}
				}
				if (downUp.playerHas(player, downUp.items.lunch_luna)) {
					if (downUp.getMetadata(player, "llrage") != null) {
						if ((Double)downUp.getMetadata(player, "llrage") < 56) {
							downUp.setMetadata(player, "llrage", (Double)downUp.getMetadata(player, "llrage") + ede.getDamage());
							if ((Double)downUp.getMetadata(player, "llrage") >= 56) {
								player.sendMessage(ChatColor.AQUA + "Your Lunchbox Lunatic Rage meter is FULL!");
							}
						}
					} else {
						downUp.setMetadata(player, "llrage",  ede.getDamage());
					}
				}

				System.out.println(downUp.getMetadata(player, "llrage"));
			}


		}
		if (ede.getDamager() instanceof Snowball) {
			Snowball sb = (Snowball) ede.getDamager();
			if (downUp.getMetadata(sb, "weapon").equals(downUp.items.snowstun) && ede.getEntity() instanceof LivingEntity) {
				LivingEntity ent = (LivingEntity) ede.getEntity();
				double distance = downUp.getDist((Location)downUp.getMetadata(sb, "initloc"), sb.getLocation());
				int stunTicks = 0;
				double maxDist = 25;
				if (distance >= 6) {
					if (distance > maxDist) distance = maxDist;
					stunTicks = (int)((distance-3 / maxDist) * 3);

					downUp.setMetadata(ent, "stunned", true);
					System.out.println(stunTicks);
					ent.damage(2);
					if (distance == maxDist) {
						downUp.setMetadata(ent, "frozen", true);
						ent.getWorld().playSound(ent.getLocation(), Sound.ANVIL_LAND, 1f, 100f);
					}
					new SnowstunnedTask(downUp, stunTicks, ent).runTaskTimer(downUp, 0, 0);
				}
			}
		}
	}
}
