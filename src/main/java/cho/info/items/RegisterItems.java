package cho.info.items;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.material.item.GenericCustomItem;

public class RegisterItems  {

    public Plugin plugin;

    public RegisterItems(Plugin plugin){
        this.plugin = plugin;
    }

    public void createItem(String id, String texture) {
        new GenericCustomItem(plugin, id, texture);
    }

}
