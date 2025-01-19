package cho.info.crafting;

import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import cho.info.items.ItemManager;

public class RegisterCrafting {

    private final JavaPlugin plugin;
    private final ItemManager itemManager;

    public RegisterCrafting(JavaPlugin plugin, ItemManager itemManager) {
        this.plugin = plugin;
        this.itemManager = itemManager;
    }

    // Methode zum Registrieren von Shaped-Rezepten
    public void registerShapedRecipe(String[] shape, String[] ingredients, String resultItem) {
        ItemStack result = itemManager.getItemByName(resultItem);
        if (result != null) {
            ShapedRecipe shapedRecipe = new ShapedRecipe(result);
            shapedRecipe.shape(shape);

            // Zutaten hinzufügen
            for (int i = 0; i < ingredients.length; i++) {
                ItemStack ingredient = itemManager.getItemByName(ingredients[i]);
                if (ingredient != null) {
                    shapedRecipe.setIngredient((char) ('A' + i), ingredient.getData());
                } else {
                    plugin.getLogger().warning("Ingredient not found: " + ingredients[i]);
                }
            }
            plugin.getServer().addRecipe(shapedRecipe);
        } else {
            plugin.getLogger().warning("Result item not found: " + resultItem);
        }
    }

    // Methode zum Registrieren von Shapeless-Rezepten
    public void registerShapelessRecipe(String[] ingredients, String resultItem) {
        ItemStack result = itemManager.getItemByName(resultItem);
        if (result != null) {
            ShapelessRecipe shapelessRecipe = new ShapelessRecipe(result);

            // Zutaten hinzufügen
            for (String ingredient : ingredients) {
                ItemStack ingredientStack = itemManager.getItemByName(ingredient);
                if (ingredientStack != null) {
                    shapelessRecipe.addIngredient(ingredientStack.getData());
                } else {
                    plugin.getLogger().warning("Ingredient not found: " + ingredient);
                }
            }
            plugin.getServer().addRecipe(shapelessRecipe);
        } else {
            plugin.getLogger().warning("Result item not found: " + resultItem);
        }
    }
}
