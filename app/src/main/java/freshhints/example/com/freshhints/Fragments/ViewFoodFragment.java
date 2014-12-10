package freshhints.example.com.freshhints.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import freshhints.example.com.freshhints.R;
import freshhints.example.com.freshhints.adapters.FoodListAdapter;
import freshhints.example.com.freshhints.interfaces.FragmentController;
import freshhints.example.com.freshhints.models.Food;

import static freshhints.example.com.freshhints.models.FoodList.getFoodList;

/**
 * Created by anniedevine on 12/3/14.
 */
public class ViewFoodFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private FragmentController fc;

    ListView foodListView;
    ArrayAdapter<Food> foodListAdapter;
    ArrayList<Food> foodList = new ArrayList<Food>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_food_list, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    foodListView = (ListView) getView().findViewById(R.id.fragment_view_food_list);

    foodListAdapter =
    new ArrayAdapter<Food>(getActivity(), R.layout.fragment_view_food_row, foodList);

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