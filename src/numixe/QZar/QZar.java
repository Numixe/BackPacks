package numixe.QZar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QZar {
	
	public static QZar plugin;
	
	public void onEnable() {
		QZar.plugin = this;
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
	    if (cmd.getName().equalsIgnoreCase("QZar")) {
	      
	     }
		}
		return true;
	  }

}
