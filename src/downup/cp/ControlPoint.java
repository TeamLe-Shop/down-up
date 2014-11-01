package downup.cp;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;

import downup.DownUp;

public class ControlPoint {

	private int owner;
	private float bluMeter, redMeter;
	private float maxCap = 100f;
	private DUMap map;
	private int id;
	private double x, y, z;
	
	public ControlPoint(int id, double x, double y, double z, int owner, DUMap map) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
		this.owner = owner;
		this.map = map;
	}
	
	public float getBLUMeter() {
		return bluMeter;
	}
	
	public boolean bluProgress(float am) {
		
		if (bluMeter == maxCap) return false;
		Location loc = new Location(DownUp.downup.getServer().getWorld("world"), x, y, z);
		if (redMeter > 0) {
			redReverse(.625f);
			
			return true;
		}
		bluMeter += am;
		
		if (bluMeter >= maxCap) {
			bluMeter = maxCap;
			loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
			loc.getWorld().getBlockAt(loc).setData(DyeColor.BLUE.getData());
			loc.getWorld().playSound(loc, Sound.LEVEL_UP, 1f, 1f);
		}
		return true;
	}
	
	public void bluReverse(float am) {
		Location loc = new Location(DownUp.downup.getServer().getWorld("world"), x, y, z);
		bluMeter -= am;
		if (bluMeter <= 0) {
			bluMeter = 0;
			loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
			loc.getWorld().getBlockAt(loc).setData(DyeColor.SILVER.getData());
		}
	}
	
	public float getREDMeter() {
		return redMeter;
	}
	
	public boolean redProgress(float am) {
		Location loc = new Location(DownUp.downup.getServer().getWorld("world"), x, y, z);
		if (redMeter == maxCap) return false;
		if (bluMeter > 0) {
			bluReverse(.625f);
			return true;
		}
		redMeter += am;
		if (redMeter >= maxCap) {
			redMeter = maxCap;
			loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
			loc.getWorld().getBlockAt(loc).setData(DyeColor.RED.getData());
			loc.getWorld().playSound(loc, Sound.LEVEL_UP, 1f, 1f);
		}
		return true;
	}
	
	public void redReverse(float am) {
		Location loc = new Location(DownUp.downup.getServer().getWorld("world"), x, y, z);
		redMeter -= am;
		if (redMeter <= 0) {
			redMeter = 0;
			loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
			loc.getWorld().getBlockAt(loc).setData(DyeColor.SILVER.getData());
		}
	}
	
	public int getID() {
		return id;
	}
	
	public DUMap getMap() {
		return map;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public int getOwner() {
		return owner;
	}
	
	public void setOwner() {
		
	}
	
	public String getTeam() {
		if (owner == 0) return "Unowned";
		return DownUp.downup.getTeam(owner);
	}
}
