package cho.info.food;

import cho.info.CustomItems;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class FoodManager {

    private final List<Food> foodList = new ArrayList<>();


    public void loadFoodFromConfig() {
        // Hole die Sektion "food" aus der Config
        ConfigurationSection foodSection = CustomItems.getInstance().getConfig().getConfigurationSection("food");

        if (foodSection != null) {
            for (String key : foodSection.getKeys(false)) {
                String name = foodSection.getString(key + ".name");
                int hunger = foodSection.getInt(key + ".hunger", 0); // Standardwert 0, falls Hunger fehlt
                String texture = foodSection.getString(key + ".texture");

                if (name != null && hunger > 0 && texture != null) {
                    foodList.add(new Food(name, hunger, texture));
                }
            }
        }
    }


    public List<Food> getFoodList() {
        return foodList;
    }


    public static class Food {
        private final String name;
        private final int hunger;
        private final String texture;

        public Food(String name, int hunger, String texture) {
            this.name = name;
            this.hunger = hunger;
            this.texture = texture;
        }

        public String getName() {
            return name;
        }

        public int getHunger() {
            return hunger;
        }

        public String getTexture() {
            return texture;
        }

        @Override
        public String toString() {
            return "Food{name='" + name + "', hunger=" + hunger + ", texture='" + texture + "'}";
        }
    }
}
