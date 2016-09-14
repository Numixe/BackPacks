package numixe.BackPacks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class EventListener implements Listener {

	@EventHandler
	public void InventoryCloseEvent(InventoryCloseEvent event) {
		
		Player p = (Player) event.getPlayer();
	}
}
