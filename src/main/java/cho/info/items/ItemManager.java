package cho.info.items;

import cho.info.CustomItems;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private final List<Item> itemList = new ArrayList<>();

    public void loadItemsFromConfig() {
        // Hole die Sektion "items" aus der Config
        ConfigurationSection itemsSection = CustomItems.getInstance().getConfig().getConfigurationSection("items");

        if (itemsSection != null) {
            for (String key : itemsSection.getKeys(false)) {
                String name = itemsSection.getString(key + ".name");
                String texture = itemsSection.getString(key + ".texture");

                if (name != null && texture != null) {
                    itemList.add(new Item(name, texture));
                }
            }
        }
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public static class Item {
        private final String name;
        private final String texture;

        public Item(String name, String texture) {
            this.name = name;
            this.texture = texture;
        }

        public String getName() {
            return name;
        }

        public String getTexture() {
            return texture;
        }

        @Override
        public String toString() {
            return "Item{name='" + name + "', texture='" + texture + "'}";
        }
    }
}