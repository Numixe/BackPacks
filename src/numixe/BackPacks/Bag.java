package numixe.BackPacks;

import static numixe.BackPacks.BackPacks.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class Bag {
	
	Inventory inventory;

	public Bag(Player player) {
		
		inventory = Bukkit.getServer().createInventory(player, InventoryType.CHEST, "§a§lZaino");
		
		if (!plugin.getConfig().contains("backpack." + player.getName())) {
			
			plugin.getConfig().createSection("backpack." + player.getName());
		}
	}
	
	public Player getHolder() {
		
		return (Player) inventory.getHolder();
	}
	
	public Material getType(int index) {
		
		return Material.valueOf(plugin.getConfig().getString("backpack." + getHolder().getName() + "." + index + ".type"));
	}
	
	public int getAmount(int index) {
		
		return plugin.getConfig().getInt("backpack." + getHolder().getName() + "." + index + ".amount");
	}
	
	public static Bag loadByPlayer(Player player) {
		
		return null;
	}
}
