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
	public static final ItemStack NULL_ITEM = new ItemStack(Material.AIR);
	
	Inventory inventory;

	public Bag(Player player) {
		
		inventory = Bukkit.getServer().createInventory(player, InventoryType.CHEST, "§a§lZaino di " + player.getName());
		
		if (!plugin.getConfig().contains("backpacks." + player.getName())) {
			
			plugin.getConfig().createSection("backpacks." + player.getName());
			
			plugin.saveConfig();
			plugin.reloadConfig();
			
		} else {
			
			loadInventory();
		}
	}
	
	public void open() {
		
		getHolder().openInventory(inventory);
	}
	
	public Player getHolder() {
		
		return (Player) inventory.getHolder();
	}
	
	public void storeInventory() { // store the inventory list
		
		List<ItemStack> list = new ArrayList<ItemStack>();
		
		for (ItemStack item :  inventory.getContents()) {
			
			if (item != null)
				list.add(item);
		}
		
		plugin.getConfig().set("backpacks." + getHolder().getName(), list);
		
		plugin.saveConfig();
		plugin.reloadConfig();
	}
	
	public void loadInventory() { 	// load inventory from config
		
		@SuppressWarnings("unchecked")
		List<ItemStack> items = (ArrayList<ItemStack>) plugin.getConfig().getList("backpacks." + getHolder().getName());
		
		inventory.clear();
		
		for (ItemStack item : items) {
			
			if (item != null)
				inventory.addItem(item);
		}
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
