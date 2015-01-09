package freshhints.example.com.freshhints.fragments;

import android.app.LoaderManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.SimpleCursorAdapter;

import freshhints.example.com.freshhints.R;
import freshhints.example.com.freshhints.models.FoodProvider;

/**
 * Created by anniedevine on 1/9/15.
 */
public class FoodListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Creating an array to specify the fields to display in the list (food name & days left)
        String[] foodInfoToBeDisplayed = new String[] {FoodProvider.COLUMN_NAME,
                FoodProvider.COLUMN_DAYSLEFT};

        // Creating an array of the fields to bind those fields to
        int[] fieldsToBindFoodInfo = new int[] { R.id.name_text, R.id.days_left_text };

        // Creating simple cursor adapter and setting it to display the data
        mAdapter = new SimpleCursorAdapter(getActivity(), R.layout.fragment_view_food_row,
                null, foodInfoToBeDisplayed, fieldsToBindFoodInfo, 0);
        setListAdapter(mAdapter);
        getLoaderManager().initLoader(0, null, this);

    }
}
