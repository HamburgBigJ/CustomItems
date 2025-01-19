package cho.info;

import cho.info.blocks.BlockManager;
import cho.info.blocks.RegisterBlocks;
import cho.info.food.FoodManager;
import cho.info.food.RegisterFood;
import cho.info.items.ItemManager;
import cho.info.items.RegisterItems;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CustomItems extends JavaPlugin {

    private RegisterItems registerItems;
    private RegisterBlocks registerBlocks;
    private RegisterFood registerFood;

    public ItemManager itemManager;
    public BlockManager blockManager;
    public FoodManager foodManager;

    private static CustomItems instance;

    public static CustomItems getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this; // Set the instance here
        saveDefaultConfig();

        // Initialisiere die Registrierungs-Klassen
        initializeRegistrations();

        // Lade und registriere die Items, Blöcke, Nahrungsmittel und Rezepte
        registerItems();
        registerBlocks();
        registerFood();
        // registerTools();
        //registerCraftingRecipes(itemManager); // not jet implemented

        itemList();

        getLogger().info("CustomItems has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("CustomItems has been disabled!");
    }

    private void initializeRegistrations() {
        this.registerItems = new RegisterItems(this);
        this.registerBlocks = new RegisterBlocks(this);
        this.registerFood = new RegisterFood(this);
    }

    private void registerItems() {
        this.itemManager = new ItemManager();
        itemManager.loadItemsFromConfig();

        itemManager.getItemList().forEach(item -> {
            registerItems.createItem(item.getName(), item.getTexture());
        });
    }

    private void registerBlocks() {
        this.blockManager = new BlockManager();
        blockManager.loadBlocksFromConfig();

        blockManager.getBlocksList().forEach(block -> {
            registerBlocks.createBlock(block.getId(), block.getName(), block.getTexture());
        });
    }

    private void registerFood() {
        this.foodManager = new FoodManager();
        foodManager.loadFoodFromConfig();


        foodManager.getFoodList().forEach(food -> {
            registerFood.createFood(food.getName(), food.getTexture(), food.getHunger());
        });
    }



    public void itemList() {
        if (itemManager == null || blockManager == null || foodManager == null) {
            getLogger().severe("One or more managers are not initialized!");
            return;
        }

        List<ItemManager.Item> itemList = itemManager.getItemList();
        List<BlockManager.Block> blockList = blockManager.getBlocksList();
        List<FoodManager.Food> foodList = foodManager.getFoodList();

        int items = itemList.size();
        int blocks = blockList.size();
        int foodItems = foodList.size();

        getLogger().info("Items: " + items);
        getLogger().info("Blocks: " + blocks);
        getLogger().info("Food Items: " + foodItems);
    }

}
