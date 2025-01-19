package cho.info.tools;

import cho.info.CustomItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class ToolManager {

    private final List<Tool> toolList = new ArrayList<>();

    // Lädt Werkzeuge aus der Konfiguration
    public void loadToolsFromConfig() {
        // Hole die Sektion "tools" aus der Config
        ConfigurationSection toolsSection = CustomItems.getInstance().getConfig().getConfigurationSection("tools");

        if (toolsSection != null) {
            for (String key : toolsSection.getKeys(false)) {
                String name = toolsSection.getString(key + ".name");
                short durability = (short) toolsSection.getInt(key + ".durability", 100);  // Default: 100
                String texture = toolsSection.getString(key + ".texture");

                if (name != null && texture != null) {
                    toolList.add(new Tool(name, durability, texture));
                }
            }
        }
    }



    // Gibt die Liste aller Tools zurück
    public List<Tool> getToolList() {
        return toolList;
    }

    // Tool Klasse für die Verwaltung von Werkzeugen
    public static class Tool {
        private final String name;
        private final short durability;
        private final String texture;

        public Tool(String name, short durability, String texture) {
            this.name = name;
            this.durability = durability;
            this.texture = texture;
        }

        public String getName() {
            return name;
        }


        public short getDurability() {
            return durability;
        }

        public String getTexture() {
            return texture;
        }

        @Override
        public String toString() {
            return "Tool{name='" + name + ", durability=" + durability + ", texture='" + texture + "'}";
        }
    }
}
