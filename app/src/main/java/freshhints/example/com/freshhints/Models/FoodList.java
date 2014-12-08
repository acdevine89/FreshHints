package freshhints.example.com.freshhints.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anniedevine on 12/3/14.
 */
public class FoodList extends ArrayList {

    static ArrayList<Food> foodList = new ArrayList<Food>();

    Food banana = new Food("banana", 5, "keep bananas fresh");
    Food peach = new Food("peach", 6, "keep peaches fresh");
    Food blackberries = new Food("blackberries", 3, "keep blackberries fresh");
    Food apple = new Food("apple", 1, "keep apple fresh");
    Food kiwi = new Food("kiwi", 7, "keep kiwi fresh");
    Food lemon = new Food("lemon", 9, "keep lemon fresh");

    public void createFoodList() {
        foodList.add(banana);
        foodList.add(peach);
        foodList.add(blackberries);
        foodList.add(apple);
        foodList.add(kiwi);
        foodList.add(lemon);
    }

    public static List<Food> getFoodList() {
        return foodList;
    }

}
