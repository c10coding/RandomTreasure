package me.caleb.RandomTreasure;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

	public static Main plugin = Main.getMain();
	
	public static FileConfiguration config = plugin.getConfig();
	
	public static World getWorld() {
		if(Bukkit.getWorlds().contains(Bukkit.getWorld("World"))) {
			return Bukkit.getWorld(config.getString("World"));
		}else {
			return null;
		}
	}
	
	public static void addShrine(Location loc, int num) {
		LocationSerializer ls = new LocationSerializer(plugin.getConfig());
		ls.storeLocation("FirstShrines." + "Shrine" + num + ".Location", loc);
		
		plugin.getConfig().set("FirstShrines." + "Shrine" + num + ".ConqueredBy", "none");
		plugin.getConfig().set("FirstShrines." + "Shrine" + num + ".BeingConquered", false);
		plugin.saveConfig();
	}
	
	public static int getNumShrines() {
		return config.getInt("NumShrines");
	}
	
	public static double getMinBound() {
		return Double.parseDouble(config.getString("MinBound"));
	}
	
	public static double getMaxBound() {
		return Double.parseDouble(config.getString("MaxBound"));
	}
	
	public static void setBeingConquered(int num) {
		config.set("FirstShrines.Shrine" + num + ".BeingConquered", true);
		plugin.saveConfig();
	}
	
	public static boolean isBeingConquered(int num) {
		return config.getBoolean("FirstShrines.Shrine" + num + ".BeingConquered");
	}
	
	public static String getConquerer(int num) {
		return config.getString("FirstShrines.Shrine" + num + ".ConqueredBy");
	}
	
	public static List<Location> getTreasureShrineLocs() {
		List<Location> shrines = new ArrayList<Location>();
		LocationSerializer ls = new LocationSerializer(plugin.getConfig());
		
		try {
			for(int x = 1; x <= getNumShrines(); x++) {
				shrines.add(ls.toLocation(config.getString("FirstShrines.Shrine" + x + ".Location")));
				
			}
		}catch(NullPointerException e) {
			return new ArrayList<Location>();
		}
		
		
		return shrines;
	}
	
}
