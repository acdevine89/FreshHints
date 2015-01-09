package freshhints.example.com.freshhints.fragments;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setEmptyText(getResources().getString(R.string.no_food_yet));
        registerForContextMenu(getListView());
        setHasOptionsMenu(true);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // This will place into the intent the ID of the task to be edited. The class that is
        // needed will inspect the intent and if it finds the ID, will attempt to allow the user
        // to edit the task
        //startActivity(new Intent(getActivity(), need a class here));
        //.putExtra(FoodProvider.COLUMN_ROWID, id));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_delete:
//                AdapterViewCompat.AdapterContextMenuInfo info =
//                        (AdapterViewCompat.AdapterContextMenuInfo) item.getMenuInfo();
//                getActivity().getContentResolver().delete(
//                        ContentUris.withAppendedId(FoodProvider.CONTENT_URI, info.id), null, null);
//                return true;
//        }
//        return super.onContextItemSelected(item);
    }

}
