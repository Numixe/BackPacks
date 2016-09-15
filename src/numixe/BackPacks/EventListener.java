package numixe.BackPacks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class EventListener implements Listener {

	@EventHandler
	public void InventoryCloseEvent(InventoryCloseEvent event) {
		
		Player p = (Player) event.getPlayer();
		Inventory inv = event.getInventory();
		Bag supposed = Bag.getBag(p);
		
		if (inv.equals(supposed.inventory)) 	// check for equality
			supposed.storeInventory();		// store inventory in config
	}
}
