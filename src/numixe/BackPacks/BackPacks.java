package numixe.BackPacks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackPacks {
	
	public static BackPacks plugin;
	
	public void onEnable() {
		BackPacks.plugin = this;
		System.out.println("QZar Enabled!");
	}
	
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		if (!(sender instanceof Player)) {
			System.out.println("Console isn't a player!");
			return true;
		} else {		
	    //Player p = (Player) sender;
	    if (cmd.getName().equalsIgnoreCase("backpacks")) {
	      
	     }
		}
		return true;
	  }

}
