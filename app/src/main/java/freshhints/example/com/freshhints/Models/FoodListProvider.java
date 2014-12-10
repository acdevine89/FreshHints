package freshhints.example.com.freshhints.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anniedevine on 12/3/14.
 */
public class FoodListProvider {

    private List<Food> foodList = new ArrayList<Food>() {{
       add(new Food("bananas", 5, "keep bananas fresh"));
       add(new Food("peaches", 6, "keep peaches fresh"));
       add(new Food("blackberries", 3, "keep blackberries fresh"));
       add(new Food("apples", 1, "keep apples fresh"));
       add(new Food("kiwis", 7, "keep kiwis fresh"));
       add(new Food("lemons", 9, "keep lemons fresh"));
    }};

    public List<Food> getFoodList() {
        return foodList;
    }

}
