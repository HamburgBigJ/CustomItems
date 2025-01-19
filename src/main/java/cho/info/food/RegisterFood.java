package cho.info.food;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.material.item.GenericCustomFood;

public class RegisterFood {

    public Plugin plugin;

    public RegisterFood(Plugin plugin){
        this.plugin = plugin;
    }

    public void createFood(String name, String texture, int hunger) {
        new GenericCustomFood(plugin, name, texture, hunger);
    }

}
