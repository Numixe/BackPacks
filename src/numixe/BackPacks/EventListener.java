package numixe.BackPacks;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventListener implements Listener {
	
	public static final ItemStack BACKPACK = genBackPack();

	@EventHandler
	public void InventoryCloseEvent(InventoryCloseEvent event) {
		
		Player p = (Player) event.getPlayer();
		Inventory inv = event.getInventory();
		Bag supposed = Bag.getBag(p);
		
		if (inv.equals(supposed.inventory)) 	// check for equality
			supposed.storeInventory();		// store inventory in config
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			ItemStack item = event.getItem();
			
			if (item.equals(BACKPACK)) {
				Bag.getBag(event.getPlayer()).open();
			}
		}
	}
	
	public static ItemStack genBackPack() {
		
		ItemStack bag = new ItemStack(Material.LEATHER);
		ItemMeta meta = bag.getItemMeta();
        meta.setDisplayName("§2§lZaino");
		bag.setItemMeta(meta);
		
		// configure backpack item here
		
		return bag;
	}
} 
