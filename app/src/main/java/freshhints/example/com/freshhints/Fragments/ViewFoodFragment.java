package freshhints.example.com.freshhints.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import freshhints.example.com.freshhints.R;
import freshhints.example.com.freshhints.adapters.FoodListAdapter;
import freshhints.example.com.freshhints.interfaces.FragmentController;
import freshhints.example.com.freshhints.models.Food;
import freshhints.example.com.freshhints.models.FoodListProvider;

/**
 * Created by anniedevine on 12/3/14.
 */
public class ViewFoodFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private FragmentController fc;

    ListView foodListView;
    ArrayAdapter<Food> foodListAdapter;
    List<Food> foodList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_food_list, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    foodListView = (ListView) view.findViewById(R.id.fragment_view_food_list);
    foodList = new FoodListProvider().getFoodList();

    foodListAdapter = new FoodListAdapter(getActivity(), foodList);

        foodListView.setAdapter(foodListAdapter);
        foodListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        if (fc != null) {
            Food foodItem = (Food) adapterView.getAdapter().getItem(position);
            FoodDetailFragment foodDetailFragment = FoodDetailFragment.newInstance(foodItem);
            fc.swapFragment(foodDetailFragment);
        }
    }
}