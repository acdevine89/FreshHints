package freshhints.example.com.freshhints.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import freshhints.example.com.freshhints.R;
import freshhints.example.com.freshhints.models.Food;

/**
 * Created by anniedevine on 12/3/14.
 */
public class FoodDetailFragment extends Fragment {

    private static final String ARG_FOOD_ITEM = "arg_food_item";

    public static FoodDetailFragment newInstance(Food foodItem) {
        Bundle args = new Bundle();
      //  args.putParcelable(ARG_FOOD_ITEM, foodItem);

        FoodDetailFragment foodDetailFragment = new FoodDetailFragment();
        foodDetailFragment.setArguments(args);

        return foodDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_food_detail, container, false);
        return v;
    }

}
