package cho.info.items;

import cho.info.CustomItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private final List<Item> itemList = new ArrayList<>();

    public void loadItemsFromConfig() {
        ConfigurationSection itemsSection = CustomItems.getInstance().getConfig().getConfigurationSection("items");

        if (itemsSection != null) {
            for (String key : itemsSection.getKeys(false)) {
                String name = itemsSection.getString(key + ".name");
                String texture = itemsSection.getString(key + ".texture");

                // Optional: Weitere Attribute aus der Config laden
                String lore = itemsSection.getString(key + ".lore");
                int durability = itemsSection.getInt(key + ".durability", 0);  // Default: 0
                String enchantment = itemsSection.getString(key + ".enchantment"); // Optional

                if (name != null && texture != null) {
                    itemList.add(new Item(name, texture, lore, durability, enchantment));
                }
            }
        }
    }
    public ItemStack getItemByName(String name) {
        for (Item item : itemList) {
            if (item.getName().equals(name)) {
                return new ItemStack(Material.getMaterial(item.getTexture()));
            }
        }
        return null;
    }


    public List<Item> getItemList() {
        return itemList;
    }

    public ItemStack createItemStack(Item item) {
        ItemStack itemStack = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta = itemStack.getItemMeta();

        // Setze Name
        itemMeta.setDisplayName(item.getName());

        // Setze Lore (falls vorhanden)
        if (item.getLore() != null) {
            itemMeta.setLore(List.of(item.getLore()));
        }

        // Setze Haltbarkeit (falls vorhanden)
        itemStack.setDurability((short) item.getDurability());

        // Setze Verzauberung (falls vorhanden)
        if (item.getEnchantment() != null) {
            itemMeta.addEnchant(org.bukkit.enchantments.Enchantment.DAMAGE_ALL, 1, true);
        }

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static class Item {
        private final String name;
        private final String texture;
        private final String lore;
        private final int durability;
        private final String enchantment;

        public Item(String name, String texture, String lore, int durability, String enchantment) {
            this.name = name;
            this.texture = texture;
            this.lore = lore;
            this.durability = durability;
            this.enchantment = enchantment;
        }

        public String getName() {
            return name;
        }

        public String getTexture() {
            return texture;
        }

        public String getLore() {
            return lore;
        }

        public int getDurability() {
            return durability;
        }

        public String getEnchantment() {
            return enchantment;
        }

        @Override
        public String toString() {
            return "Item{name='" + name + "', texture='" + texture + "', lore='" + lore + "', durability=" + durability + ", enchantment='" + enchantment + "'}";
        }
    }
}
