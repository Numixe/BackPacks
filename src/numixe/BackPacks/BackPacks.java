package numixe.BackPacks;
 
import java.util.HashMap;
import java.util.Map.Entry;
 
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
 
public class BackPacks extends JavaPlugin implements Listener {
       
        private HashMap<String, Inventory> backpacks = new HashMap<String, Inventory>();
       
        
        public void onEnable() {
            Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }
   
    public void onDisable() {
      for (Entry<String, Inventory> entry : backpacks.entrySet()) {
      if (!getConfig().contains("Player.BackPacks." + entry.getKey())) {
        getConfig().createSection("Player.BackPacks." + entry.getKey());
          }                  
      char c = 'a';
      for (ItemStack itemStack : entry.getValue()) {
       if (itemStack != null) {
          saveItem(getConfig().createSection("Player.BackPacks." + entry.getKey() + "." + c++), itemStack);
         }
       }
      saveConfig();
            }
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
            if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED + "The console cannot have an backpack.");
                    return true;
            }
           
            Player p = (Player) sender;
           
            if (cmd.getName().equalsIgnoreCase("backpack")) {
                    p.openInventory(backpacks.get(p.getName()));
            }
           
            return true;
    }
    
    
    
        @EventHandler
        public void onPlayerJoin(PlayerJoinEvent e) {
         Inventory inv = Bukkit.getServer().createInventory(e.getPlayer(), InventoryType.CHEST, "§a§lZaino");
               
         if (getConfig().contains("Player.BackPacks." + e.getPlayer().getName())) {
         for (String item : getConfig().getConfigurationSection("backpacks." + e.getPlayer().getName()).getKeys(false)) {
         inv.addItem(loadItem(getConfig().getConfigurationSection("Player.BackPacks." + e.getPlayer().getName() + "." + item)));
                        }
                }
               
          backpacks.put(e.getPlayer().getName(), inv);
        }
       
        @EventHandler
        public void onPlayerLeave(PlayerQuitEvent e) {
        if (!getConfig().contains("Player.BackPacks." + e.getPlayer().getName())) {
          getConfig().createSection("Player.BackPacks." + e.getPlayer().getName());
                }
          char c = 'a';
          for (ItemStack itemStack : backpacks.get(e.getPlayer().getName())) {
          if (itemStack != null) {
            saveItem(getConfig().createSection("Player.BackPacks." + e.getPlayer().getName() + "." + c++), itemStack);
                        }
                }
                saveConfig();
        }
       
        
       
        
       
        private void saveItem(ConfigurationSection section, ItemStack itemStack) {
                section.set("type", itemStack.getType().name());
                section.set("amount", itemStack.getAmount());
                // Save more information.
        }
       
        private ItemStack loadItem(ConfigurationSection section) {
                return new ItemStack(Material.valueOf(section.getString("type")), section.getInt("amount"));
                // Load more information.
        }
}