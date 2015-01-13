package freshhints.example.com.freshhints.fragments;

import android.os.Bundle;
import android.view.View;

import freshhints.example.com.freshhints.R;
import freshhints.example.com.freshhints.adapters.RemindersDbAdapter;

/**
 * Created by anniedevine on 1/9/15.
 */
public class ViewFoodListFragment extends BaseFragment {
    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;

    private RemindersDbAdapter mDbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_view_food_list, container, false);

    }
}
