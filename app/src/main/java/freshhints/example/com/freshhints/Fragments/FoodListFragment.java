package freshhints.example.com.freshhints.fragments;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
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
public class FoodListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>, android.support.v4.app.LoaderManager.LoaderCallbacks<Object> {

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

 //   @Override
   // public boolean onContextItemSelected(MenuItem item) {
// This will handle user context menu events that occur when they first long-press the task
// and then select a menu item from the context menu. getMenuInfo() obtains an instance of
// AdapterContextMenuInfo, which has info about the item the user long-pressed. Also gets a
// ContentResolver and requests that it delete the food whose ID is retrieved from the
// AdapterContextMenuInfo object's id field.
//        switch (item.getItemId()) {
//            case R.id.menu_delete:
//                AdapterViewCompat.AdapterContextMenuInfo info =
//                        (AdapterViewCompat.AdapterContextMenuInfo) item.getMenuInfo();
//                getActivity().getContentResolver().delete(
//                        ContentUris.withAppendedId(FoodProvider.CONTENT_URI, info.id), null, null);
//                return true;
//        }
//        return super.onContextItemSelected(item);
 //   }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int ignored, final Bundle args) {
        return new android.content.CursorLoader(getActivity(), FoodProvider.CONTENT_URI, null, null, null, null);
    }


    @Override
    public void onLoadFinished(android.content.Loader<Cursor> cursorLoader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> cursorLoader) {
        // Called when the last Cursor provided to onLoadFinished() above is about to be closed.
        // Set the cursor to null here to make sure the adapter is no longer using it.
        mAdapter.swapCursor(null);
    }


}