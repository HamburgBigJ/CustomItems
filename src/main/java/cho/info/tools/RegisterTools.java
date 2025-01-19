package cho.info.tools;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.material.item.GenericCustomTool;

public class RegisterTools {

    public Plugin plugin;

    public RegisterTools(Plugin plugin){
        this.plugin = plugin;
    }

    public void createTool(String name, String texture, short durability) {
        new GenericCustomTool(plugin, name, texture).setMaxDurability(durability);
    }

}
