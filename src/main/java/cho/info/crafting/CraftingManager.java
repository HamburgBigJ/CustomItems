package cho.info.crafting;

import org.bukkit.configuration.ConfigurationSection;
import cho.info.items.ItemManager;
import cho.info.CustomItems;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CraftingManager {

    private final RegisterCrafting registerCrafting;
    private final JavaPlugin plugin;
    private final ItemManager itemManager;

    public CraftingManager(JavaPlugin plugin, ItemManager itemManager) {
        this.plugin = plugin;
        this.itemManager = itemManager;
        this.registerCrafting = new RegisterCrafting(plugin, itemManager);
    }

    // Lädt Crafting-Rezepte aus der Konfiguration
    public void loadCraftingRecipes() {
        ConfigurationSection craftingSection = CustomItems.getInstance().getConfig().getConfigurationSection("crafting");

        if (craftingSection != null) {
            for (String recipeKey : craftingSection.getKeys(false)) {
                String mode = craftingSection.getString(recipeKey + ".mode");
                String resultItem = craftingSection.getString(recipeKey + ".result");
                List<String> recipe = craftingSection.getStringList(recipeKey + ".recipe");

                // Je nach Rezept-Modus (shaped oder shapeless) das Rezept registrieren
                if (mode.equals("shaped")) {
                    String[] shape = new String[recipe.size()];
                    for (int i = 0; i < recipe.size(); i++) {
                        shape[i] = recipe.get(i);
                    }
                    registerCrafting.registerShapedRecipe(shape, recipe.toArray(new String[0]), resultItem);
                } else if (mode.equals("shapeless")) {
                    registerCrafting.registerShapelessRecipe(recipe.toArray(new String[0]), resultItem);
                }
            }
        }
    }
}
