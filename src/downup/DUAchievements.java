package downup;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public enum DUAchievements {

	HOT_FINGERS(0, DUAchievementGroup.ARCHER, "Hot Fingers", "Kill 4 skeletons with a bow in under 10 seconds"),
	MOB_KEBAB(1, DUAchievementGroup.ARCHER, "Mob Kebab", "Shoot through 4 mobs with The Piercer"),
	QUICK_CHOPS(2, DUAchievementGroup.TREE_FELLER, "Quick Chops", "Kill 3 enemies with The SkullCutter in under 15 seconds"),
	AERIAL_CRIT(3, DUAchievementGroup.TREE_FELLER, "Aerial Crit", "Perform a critswing"),
	STILL_ALIVE(4, DUAchievementGroup.SWORDSMAN, "Still Alive", "Survive a hit that would have killed you without the Hungry Fighter."),
	GRACEFUL_FALL(5, DUAchievementGroup.SWORDSMAN, "Graceful Fall", "Negate 10 points of fall damage with the Athletic Assaulter."),
	GET_HYPER(6, DUAchievementGroup.GENERAL, "Get Hyper", "Kill 3 enemies while under the effects off The Second Jump."),
	WARMED_UP(7, DUAchievementGroup.GENERAL, "Warmed Up!", "Generate full hype with The Second Jump."),
	BATTALION_BOOSTER(8, DUAchievementGroup.GENERAL, "Battalion Booster", "Buff 4 players with the Lunchbox Lunatic."),
	ADDRESS_UNKNOWN(9, DUAchievementGroup.SWORDSMAN, "Address Unknown", "Kill someone by deflecting their arrow.");
	
	private static Map<OfflinePlayer, ArrayList<Integer>> players = new HashMap<OfflinePlayer, ArrayList<Integer>>();
	int id;
	String name, how;
	DUAchievementGroup group;

	DUAchievements(int id, DUAchievementGroup group, String name, String how) {
		this.id = id;
		this.name = name;
		this.how = how;
		this.group = group;
	}

	public static DUAchievements getByID(int id) {
		for (DUAchievements dua : values()) {
			if (dua.id == id) {
				return dua;
			}
		}
		return null;
	}

	public static void load() {
		ArrayList<String> data = IOTools.readFile(new File("cakedata/achieve.txt"));
		for (String n : data) {
			String[] args = n.split(",");
			ArrayList<Integer> ints = new ArrayList<Integer>();
			for (int i = 1; i < args.length; i++) {
				ints.add(Integer.parseInt(args[i]));
			}
			players.put(Bukkit.getOfflinePlayer(args[0]), ints);
		}
	}

	public static void save() {
		System.out.println(ChatColor.BLUE + "Saving achievements data...");
		ArrayList<String> data = new ArrayList<String>();
		for (OfflinePlayer n : players.keySet()) {
			String achData = "";
			int pubv = 0;
			int pubi = 0;
			for (int i = 0; i < players.get(n).size(); i++) {
				int v = players.get(n).get(i);
				achData = achData.concat(v + "");
				if (!(i == players.get(n).size()-1)) {
					achData = achData.concat(",");
				}
				pubv = v;
				pubi = i;
				System.out.println(getByID(pubv));
			}
			data.add(n.getName() + "," + achData);
		}
		IOTools.writeToFile(new File("cakedata/achieve.txt"), data);
	}

	public static void awardAchievement(DownUp downUp, Player player, DUAchievements dua) {
		if (players.containsKey(player)) {
			if (players.get(player).contains(dua.id)) {
				return;
			}
			ArrayList<Integer> achs = players.get(player);
			players.remove(player);
			achs.add(dua.id);
			players.put(player, achs);
		} else { 
			ArrayList<Integer> achs = new ArrayList<Integer>();
			achs.add(dua.id);
			players.put(player, achs);
		}

		downUp.omclient.send(player.getName() + " has earned the achievement '" + dua.name + "'");
		DownUp.bd(ChatColor.GREEN + player.getName() + ChatColor.RESET + " has earned the achievement '" + ChatColor.RED + dua.name + ChatColor.RESET + "'");
	}

	public enum DUAchievementGroup {
		GENERAL, ARCHER, MINER, SWORDSMAN, TREE_FELLER;
	}
}
