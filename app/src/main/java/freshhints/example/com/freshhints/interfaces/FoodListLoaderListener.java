package freshhints.example.com.freshhints.interfaces;

import java.util.List;

import freshhints.example.com.freshhints.models.Food;

/**
 * Created by anniedevine on 12/11/14.
 */
public interface FoodListLoaderListener {

    public void foodItemsLoaded(List<Food> foodItemList);

}
