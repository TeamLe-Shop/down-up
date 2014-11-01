package downup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.kitteh.tag.PlayerReceiveNameTagEvent;

import downup.cp.DUMap;
import downup.omnom.OmnomClient;
import downup.tasks.MusicPlayerTask;
import downup.tasks.OnServerTick;
/**
 * MetaData Table
 * <table border="1">
 * <tr><th>Key</th><th>Description</th><th>Type</th>
 * <tr><td>grill-jumping</td><td>If the player is grill jumping or not</td><td>Boolean</td></tr>
 * </table>
 */
public class DownUp extends JavaPlugin implements Listener {

	public ScoreBoardHandler sbh;
	
	public static final int NO_TEAM = 0;
	public static final int BLU_TEAM = 1;
	public static final int RED_TEAM = 2;
	public static final int MAX_SECONDJUMP_METER = 250;
	public boolean exp_enable = false;
	public boolean mob_drops = true;
	public HashMap<Player, Integer> player_teams = new HashMap<Player, Integer>();
	public static DownUp downup;
	public static boolean connectedToIRC;
	public static final int NOBOOST = 0;
	public static final int MINICRIT = 1;
	public static final int CRIT = 2;
	public DUMap currentMap;
	public Items items;
	private String[] LL_ACTIVATE_CALL_ARR = {
			ChatColor.RED + "I am going to CLAW my way down your throat and tear out your very SOUL!",
			ChatColor.BLUE +"HeehyeahYEAHHHH!",
			ChatColor.BLUE +"Give 'em hell bois!",
			ChatColor.RED + "The LAST word out of your sorry mouths will be 'SIR', and it will be CLEAR!",
			ChatColor.BLUE +"Last one alive, LOCK THE DOOR!",
			ChatColor.BLUE +"CHAAARGE!",
			ChatColor.RED + "You are a maggot, hatched from a mutant MAGGOT EGG!",
			ChatColor.RED + "I never liked you....."};
	private String[] SD_HYPE_METER_FULL_ARR = {
			ChatColor.BLUE + "Woohoohooo!",
			ChatColor.RED + "Oh man, I am gonna mess. You. UP!",
			ChatColor.BLUE + "Woooo!",
			ChatColor.BLUE + "Hehehey, I'm flying!",
			ChatColor.RED + "What - are y'gonna cry now?!",
			ChatColor.RED + "You, SUCK!",
			ChatColor.RED + "BOOM, I'm back dummmy!",
			ChatColor.BLUE + "Yeah!",
			ChatColor.BLUE + "Sweet.",
			ChatColor.BLUE + "Let's get 'em.",
			ChatColor.RED + "Eat my dust!",
			ChatColor.RED + "Hey who's on fire now?",
			ChatColor.BLUE + "Aw hell yeah!"};
	private String[] SOILED_WATER_ARR = {
			ChatColor.RED + "Eughhh...",
			ChatColor.RED + "Aw jeez!",
			ChatColor.RED + "I... hate you.",
			ChatColor.RED + "Noooooo!",
			ChatColor.RED + "Now I've seen everythin'.",
			ChatColor.RED + "This did not just happen!"
	};
	public static final int LL_ACTIVATE_CALL = 0;
	public static final int SD_HYPE_METER_FULL = 1;
	public static final int SOILED_WATER = 2;
	public OmnomClient omclient;
	public void onEnable() {
		items = new Items();
		this.getServer().getPluginManager().registerEvents(this, this);
		DUAchievements.load();
		Points.load();
		omclient = new OmnomClient(this);
		omclient.start();
		downup = this;
		currentMap = DUMap.SNOWTOP;
		OnServerTick ost = new OnServerTick(this);
		ost.runTaskTimer(this, 0, 1);
		sbh = new ScoreBoardHandler(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("du")) {
			if (args.length == 0) {
				sender.sendMessage("setTeam <player> <team> - Changes a player's team.");
				sender.sendMessage("mobDrops <on/off> - Enables/disables DownUp mob drops.");
				return true;
			}
			switch (args[0].toLowerCase()) {
			case "remusic":
				DUSFX.init();
				break;
			case "play":
				if (!(sender instanceof Player)) {
					sender.sendMessage("Execute the friggin' command ingame. Dammie.");
					break;
				}
				Player play = (Player) sender;
				MusicPlayerTask mpt = new MusicPlayerTask(play.getLocation(), DUSFX.return_sender);
				mpt.runTaskTimer(this, 0, 0);
				break;
			case "setteam":
				if (args.length != 3) {
					sender.sendMessage("Incorrect amount of arguments.");
					break;
				}
				if (Bukkit.getPlayer(args[1]) == null) {
					sender.sendMessage("Unknown player.");
					break;
				}
				Player player = Bukkit.getPlayer(args[1]);
				if (!args[2].equals("BLU") && !args[2].equals("RED")) {
					sender.sendMessage("Invalid team.");
					break;
				}
				if (args[2].equals("BLU")) {
					setPlayerTeam(player, BLU_TEAM);
				} else if (args[2].equals("RED")) {
					setPlayerTeam(player, RED_TEAM);
				}
				break;
			case "mobdrops":
				if (args.length != 2) {
					sender.sendMessage("Incorrect amount of arguments.");
					break;
				}
				if (args[1].equalsIgnoreCase("on")) {
					mob_drops = true;
					sender.sendMessage("DownUp mob drops enabled.");
				} else if (args[1].equalsIgnoreCase("off")) {
					mob_drops = false;
					sender.sendMessage("DownUp mob drops disabled.");
				} else {
					sender.sendMessage("Put either 'on' or 'off', dammie.");
				}
				break;
			case "exp":
				if (args.length != 2) {
					sender.sendMessage("Incorrect amount of arguments.");
					break;
				}
				if (args[1].equalsIgnoreCase("on")) {
					exp_enable = true;
					sender.sendMessage("Experience turned on.");
				} else if (args[1].equalsIgnoreCase("off")) {
					exp_enable = false;
					sender.sendMessage("Experience turned off.");
				} else {
					sender.sendMessage("Put either 'on' or 'off', would ya?");
				}
				break;
			case "clearents":
				System.out.println("[DownUp] Starting to clear entities...");
				for (World w : this.getServer().getWorlds()) {
					int entsRemoved = 0;
					System.out.println("[DownUp] Clearing entities for " + w.getWorldFolder().getName());
					for (Entity e : w.getEntities()) {
						if (!(e instanceof Player)) {
							e.remove();
							entsRemoved++;
						}
					}
					System.out.println("[DownUp] Removed " + entsRemoved + ".");
				}
				System.out.println("[DownUp] Done clearing entities!");
				break;
			case "giveme":
				if (args.length != 2) {
					sender.sendMessage("Incorrect amount of arguments.");
					break;
				}
				if (!(sender instanceof Player)) {
					sender.sendMessage("Execute the friggin' command ingame. Dammie.");
					break;
				}
				for (downup.Item i : this.items.itemList) {
					if (args[1].equals(i.name)) {
						((Player) sender).getInventory().addItem(i.item);
					}
				}
				break;
			case "cpwand":
				if (!(sender instanceof Player)) {
					sender.sendMessage("Execute the friggin' command ingame. Dammie.");
					break;
				}

				break;
			case "setmap":
				if (args.length < 2) sender.sendMessage("Incorrect amount of arguments!");
				currentMap = DUMap.getByName(args[1]);
				break;
			}
			return true;
		}
		return false;
	}

	private void setPlayerTeam(Player player, int t) {
		if (player_teams.containsKey(player)) {
			player_teams.remove(player);
		}
		player_teams.put(player, t);
		player.sendMessage("You have been put on the " + getTeam(t) + " team.");
		omclient.send(player.getName() + " has been put on the " + getTeam(t) + " team.");
	}
	
	public Integer getPlayerTeam(Player player) {
		if (player_teams.containsKey(player)) {
			return player_teams.get(player);
		}
		return NO_TEAM;
	}

	public String getTeam(int t) {
		if (t == 0) { return ChatColor.GRAY + "SPECTATOR"; }
		if (t == 1) { return ChatColor.BLUE + "BLU"; }
		if (t == 2) { return ChatColor.RED +  "RED"; }
		return ChatColor.GRAY + "SPECTATOR";
	}

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent pie) {
		EntityInteractEntityH.handle(this, pie);
	}
	
	@EventHandler
	public void onTagChange(PlayerReceiveNameTagEvent event){
		Player player = event.getNamedPlayer();

		event.setTag("");
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent pre) {
		PlayerRespawnH.handle(this, pre);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent pme) {
		PlayerMoveH.handle(this, pme);
	}

	@EventHandler
	public void onNameTag(PlayerReceiveNameTagEvent event) {
		event.setTag("");
	}

	@EventHandler
	public void onPlayerExpChangeEvent(PlayerExpChangeEvent pece) {
		PlayerEXPChangeH.handle(this, pece);
	}

	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent ede) {
		EntityDamageByEntityH.handle(this, ede);
	}

	@EventHandler
	public void onEntityInteractEvent(PlayerInteractEvent ei) {
		EntityInteractH.handle(this, ei);
	}

	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent ede) {
		EntityDamageH.handle(this, ede);
	}

	@EventHandler
	public void onEntityShootBowEvent(EntityShootBowEvent ede) {
		EntityShootBowH.handle(this, ede);
	}

	@EventHandler
	public void onEntityDeathEvent(EntityDeathEvent ede) {
		EntityDeathH.handle(this, ede);
	}

	@EventHandler
	public void onProjectileHitEvent(ProjectileHitEvent phe) {
		ProjectileHitH.handle(this, phe);
	}

	@EventHandler
	public void onPlayerItemHeldEvent(PlayerItemHeldEvent pihd) {
		PlayerItemHeldH.handle(this, pihd);
	}

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent pje) {
		PlayerJoinH.handle(this, pje);
	}
	
	public void onDisable() {
		DUAchievements.save();
	}

	public void setMetadata(Entity player, String key, Object value){
		player.setMetadata(key,new FixedMetadataValue(this,value));
	}
	public Object getMetadata(Entity player, String key){
		List<MetadataValue> values = player.getMetadata(key);  
		for(MetadataValue value : values){
			if(value.getOwningPlugin().getDescription().getName().equals(this.getDescription().getName())){
				return value.value();
			}
		}
		return null;
	}

	public ArrayList<Entity> getNearbyEntities(Location l, int radius){
		ArrayList<Entity> entities = new ArrayList<Entity>();
		for (Entity e : l.getWorld().getEntities()) {
			if (e.getLocation().getX() >= l.getX() - radius && e.getLocation().getX() <= l.getX() + radius) {
				if (e.getLocation().getY() >= l.getY() - radius && e.getLocation().getY() <= l.getY() + radius) {
					if (e.getLocation().getZ() >= l.getZ() - radius && e.getLocation().getZ() <= l.getZ() + radius) {
						entities.add(e);
					}
				}
			}
		}
		return entities;
	}

	public static void bd(String string) {
		Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "DownUp" + ChatColor.GRAY + "]"
				+ string);
	}

	public boolean itemSameAs(ItemStack bow, ItemStack the_grill) {
		if (!bow.hasItemMeta()) {
			return false;
		}
		if (!bow.getItemMeta().hasLore()) {
			return false;
		}
		return bow.getItemMeta().getLore().equals(the_grill.getItemMeta().getLore());
	}

	public boolean playerHas(Player player, ItemStack n) {
		for (ItemStack i : player.getInventory()) {
			if (i != null && n != null) {
				if (itemSameAs(i, n)) {

					return true;
				}
			}
		}
		return false;
	}

	public void heal(Damageable entity, double i) {
		if (entity.getHealth() + i > entity.getMaxHealth()) {
			entity.setHealth(entity.getMaxHealth());
		} else {
			entity.setHealth(entity.getHealth() + i);
		}
	}

	public boolean entityInRange(Entity e, Location l, int radius) {
		if (e.getLocation().getX() >= l.getX() - radius && e.getLocation().getX() <= l.getX() + radius) {
			if (e.getLocation().getY() >= l.getY() - radius && e.getLocation().getY() <= l.getY() + radius) {
				if (e.getLocation().getZ() >= l.getZ() - radius && e.getLocation().getZ() <= l.getZ() + radius) {
					return true;
				}
			}
		}
		return false;
	}

	public void pushAwayEntity(Entity entity, Location loc, double speed, double raise) {
		// Get velocity unit vector:
		Vector unitVector = entity.getLocation().toVector().subtract(loc.toVector()).normalize();
		// Set speed and push entity:
		entity.setVelocity(unitVector.multiply(speed).add(new Vector(0, raise, 0)));
	}

	public boolean sameTeam(Player player, Player shooter) {
		if (player_teams.containsKey(player) && player_teams.containsKey(shooter)) {
			if (player_teams.get(player) == player_teams.get(shooter)) {
				return true;
			}
		}
		return false;
	}

	public void playerResponse(Player p, int type) {
		Random rand = new Random();
		String msg = "";
		switch (type) {
		case LL_ACTIVATE_CALL:
			msg = (LL_ACTIVATE_CALL_ARR [rand.nextInt(LL_ACTIVATE_CALL_ARR.length)]);
			break;
		case SD_HYPE_METER_FULL:
			msg = (SD_HYPE_METER_FULL_ARR [rand.nextInt(SD_HYPE_METER_FULL_ARR.length)]);
			break;
		case SOILED_WATER:
			msg = (SOILED_WATER_ARR [rand.nextInt(SOILED_WATER_ARR.length)]);
			break;
		}

		for (Player play : this.getServer().getOnlinePlayers()) {
			if (this.entityInRange(play, p.getLocation(), 10)) {
				play.sendMessage(p.getName() + " | " + msg);
			}
		}
		omclient.send(p.getName() + " ] " + msg);
	}

	public void playerKillResponse(Player p, int type, Player killed) {
		Random rand = new Random();
	}
	
	public boolean isPlayerLLBuffed(Player player) {
		if (getMetadata(player, "llisbuffed") == null) {
			return false;
		}
		if (getMetadata(player, "llisbuffed") == Boolean.TRUE) {
			return true;
		}
		return false;
	}

	public boolean itemInHand(Player player, ItemStack a) {
		if (player.getInventory().getItemInHand() != null) {
			if (itemSameAs(player.getInventory().getItemInHand(), a)) {
				return true;
			}
		}
		return false;
	}

	public double getDist(Location e, Location m) {
		return Math.sqrt(  Math.pow((e.getX() - m.getX()), 2)
				+ Math.pow((e.getY() - m.getY()), 2)
				+ Math.pow((e.getZ() - m.getZ()), 2));
	}

	public boolean playerCanDoubleJump(Player player) {
		if (!player.isOnGround()) {
			if (getMetadata(player, "candoublejump") == Boolean.FALSE) { 
				return false;
			}
			return true;
		}
		return false;
	}

	public void setBoost(Player player, int boost) {
		setMetadata(player, "boost", (Integer)boost);
	}

	public int getBoost(Player player) {
		if (getMetadata(player, "boost") == null) {
			return 0;
		}
		return (Integer)getMetadata(player, "boost");
	}

	public float calcRamp(float dist, float avgDist, float minRamp, float maxRamp) {
		float ramp = dist / avgDist;
		float finalRMP = ramp - 1;

		if (finalRMP > minRamp) {
			finalRMP = minRamp;
		}
		if (finalRMP < maxRamp) {
			finalRMP = maxRamp;
		}
		return finalRMP;
	}
}
