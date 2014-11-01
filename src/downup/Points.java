package downup;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Points {

	public static Map<OfflinePlayer, Integer> points = new HashMap<OfflinePlayer, Integer>();
	
	public static int getPoints(OfflinePlayer p) {
		if (p instanceof Player) {
			return (Integer)DownUp.downup.getMetadata((Player)p, "points");
		} else {
			return points.get(p);
		}
	}
	
	public static void addPoints(OfflinePlayer p, int am, String reason) {
		if (am < 0 && getPoints(p) < 1) return;
		
		int initPoints = 0;
		if (points.containsKey(p)) {
			initPoints = getPoints(p);
			points.remove(p);
		}
		points.put(p, initPoints + am);
		int newAm = getPoints(p);
		if (am < 0) {
			Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "CakeyPlug" + ChatColor.GRAY + "]"
					+ ChatColor.GREEN + p.getName() + ChatColor.RESET + "(" + ChatColor.GOLD + newAm + ChatColor.RESET + ")" + ChatColor.RESET + " has lost " + ChatColor.GOLD + am + " points " + ChatColor.RESET + "for " + reason + ".");
		} else {
			Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "CakeyPlug" + ChatColor.GRAY + "]"
					+ ChatColor.GREEN + p.getName() + ChatColor.RESET + "(" + ChatColor.GOLD + newAm + ChatColor.RESET + ")" + ChatColor.RESET + " has gained " + ChatColor.GOLD + am + " points " + ChatColor.RESET + "for " + reason + ".");
		}
		DownUp.downup.setMetadata((Player)p, "points", points.get(p));
	}
	
	public static void load() {
		File pointsFile = new File("cakedata/points.txt");
		ArrayList<String> data = IOTools.readFile(pointsFile);
		for (String s : data) {
			String[] args = s.split(",");
			points.put(Bukkit.getOfflinePlayer(args[0]), Integer.parseInt(args[1]));
		}
	}
	
	public static void save() {
		File pointsFile = new File("cakedata/points.txt");
		ArrayList<String> data = new ArrayList<String>();
		for (OfflinePlayer p : points.keySet()) {
			data.add(p.getName()+","+getPoints(p));
		}
		IOTools.writeToFile(pointsFile, data);
	}
}
