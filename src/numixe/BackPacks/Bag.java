package numixe.BackPacks;

import static numixe.BackPacks.BackPacks.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Bag {
	
	public static HashMap<String, Bag> data = new HashMap<String, Bag>();	// static allocation memory
	public static final int SIZE = InventoryType.CHEST.getDefaultSize();
	
	Inventory inventory;

	public Bag(Player player) {
		
		inventory = Bukkit.getServer().createInventory(player, InventoryType.CHEST, "§a§lZaino di " + player.getName());
		
		if (!plugin.getConfig().contains("backpacks." + player.getName())) {
			
			plugin.getConfig().createSection("backpacks." + player.getName());
			
			for (int i = 0; i < SIZE; i++) {
				
				inventory.addItem(new ItemStack(Material.AIR));
			}
			
			plugin.saveConfig();
			plugin.reloadConfig();
			
		} else {
			
			@SuppressWarnings("unchecked")
			List<ItemStack> items = (ArrayList<ItemStack>) plugin.getConfig().getList("backpacks." + player.getName());
			
			inventory.addItem(items.toArray(new ItemStack[items.size()]));
		}
	}
	
	public void open() {
		
		getHolder().openInventory(inventory);
	}
	
	public Player getHolder() {
		
		return (Player) inventory.getHolder();
	}
	
	public void storeInventory() { // store the inventory list
		
		plugin.getConfig().set("backpacks." + getHolder(), inventory.getContents());
		
		plugin.saveConfig();
		plugin.reloadConfig();
	}
	
	public void loadInventory() { // 
		
		@SuppressWarnings("unchecked")
		List<ItemStack> items = (ArrayList<ItemStack>) plugin.getConfig().getList("backpacks." + getHolder());
		
		inventory.setContents(items.toArray(new ItemStack[items.size()]));
	}
	
	public static void loadPlayer(Player player) {	// load a player on join
		
		data.put(player.getName(), new Bag(player));
	}
	
	public static void freePlayer(Player player) {	// delete a player on leave
		
		getBag(player).storeInventory();	// store his inventory
		data.remove(player.getName());
	}
	
	public static Bag getBag(Player player) {	// get the bag relative to a player
		
		return data.get(player.getName());
	}
}
