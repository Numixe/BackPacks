package numixe.BackPacks;
 
import java.util.Map.Entry;
 
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
 
public class BackPacks extends JavaPlugin implements Listener {
	
	public static BackPacks plugin = null;
        
    public void onEnable() {
        	
    	plugin = this;
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getPluginManager().registerEvents(new EventListener(), this);
        
        for (Player p : Bukkit.getOnlinePlayers()) {
        	
        	Bag.loadPlayer(p);
        }
    }
   
    public void onDisable() {
    	
    	for (Entry<String, Bag> entry : Bag.data.entrySet()) {
    	  
    		System.out.println("Saving all inventories data");
    		entry.getValue().storeInventory();	
    	}
  
    	Bag.data.clear();	// clear hashmap
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    	
        if (!(sender instanceof Player)) {
            	
                sender.sendMessage(ChatColor.RED + "The console cannot have an backpack.");
                return false;
        }
           
        Player p = (Player) sender;
           
        if (cmd.getName().equalsIgnoreCase("backpack")) {
            p.sendMessage("§9BackPack> §aBackPack §7generato con successo!");
            Bag.getPack(p);
        }
           
        return true;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
         
    	Bag.loadPlayer(e.getPlayer());
    }
       
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        	
    	Bag.freePlayer(e.getPlayer());
    }
}