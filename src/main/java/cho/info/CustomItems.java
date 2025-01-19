package cho.info;

import cho.info.blocks.BlockManager;
import cho.info.blocks.RegisterBlocks;
import cho.info.food.FoodManager;
import cho.info.food.RegisterFood;
import cho.info.items.ItemManager;
import cho.info.items.RegisterItems;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomItems extends JavaPlugin {

    public RegisterItems registerItems;
    public RegisterBlocks registerBlocks;
    public RegisterFood registerFood;

    public static CustomItems Instance;
    public static CustomItems getInstance() {
        return Instance;
    }

    @Override
    public void onEnable() {
        Instance = this; // Set the instance here
        saveDefaultConfig();

        this.registerItems = new RegisterItems(this);
        this.registerBlocks = new RegisterBlocks(this);
        this.registerFood = new RegisterFood(this);

        registerItem();
    }

    @Override
    public void onDisable() {
        getLogger().info("CustomItems has been disabled!");
    }

    public void registerItem() {

        ItemManager itemManager = new ItemManager();
        itemManager.loadItemsFromConfig();

        BlockManager blockManager = new BlockManager();
        blockManager.loadBlocksFromConfig();

        FoodManager foodManager = new FoodManager();
        foodManager.loadFoodFromConfig();

        itemManager.getItemList().forEach(item -> {
            registerItems.createItem(item.getName(), item.getTexture());
        });

        blockManager.getBlocksList().forEach(block -> {
            registerBlocks.createBlock(block.getId(), block.getName(), block.getTexture());
        });

        foodManager.getFoodList().forEach(food -> {
            registerFood.createFood(food.getName(), food.getTexture(), food.getHunger());
        });
    }
}