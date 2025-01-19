package cho.info.blocks;

import cho.info.CustomItems;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class BlockManager {

    private final List<Block> blocksList = new ArrayList<>();

    /**
     * Lädt Blöcke aus der Konfiguration und fügt sie der Liste hinzu.
     */
    public void loadBlocksFromConfig() {
        // Hole die Sektion "blocks" aus der Config
        ConfigurationSection blocksSection = CustomItems.getInstance().getConfig().getConfigurationSection("blocks");

        if (blocksSection != null) {
            for (String key : blocksSection.getKeys(false)) {
                String name = blocksSection.getString(key + ".name");
                int id = blocksSection.getInt(key + ".id", -1); // Standardwert -1, falls ID fehlt
                String texture = blocksSection.getString(key + ".texture");

                if (name != null && id != -1 && texture != null) {
                    blocksList.add(new Block(name, id, texture));
                }
            }
        }
    }

    /**
     * Gibt die Liste der Blöcke zurück.
     *
     * @return Liste der Blöcke
     */
    public List<Block> getBlocksList() {
        return blocksList;
    }

    /**
     * Repräsentiert einen Block mit Name, ID und Textur.
     */
    public static class Block {
        private final String name;
        private final int id;
        private final String texture;

        public Block(String name, int id, String texture) {
            this.name = name;
            this.id = id;
            this.texture = texture;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public String getTexture() {
            return texture;
        }

        @Override
        public String toString() {
            return "Block{name='" + name + "', id=" + id + ", texture='" + texture + "'}";
        }
    }
}