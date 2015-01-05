package freshhints.example.com.freshhints.models;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by anniedevine on 1/5/15.
 */
public class FoodProvider extends ContentProvider {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "food_data";
    private static final String DATABASE_TABLE = "food_table";

    public static final String COLUMN_ROWID = "_id";
    public static final String COLUMN_FOODID = "food_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DAYSLEFT = "days_left";
    public static final String COLUMN_TIPS = "tips";

    private static final String DATABASE_CREATE = "create table "
            + DATABASE_TABLE + " (" + COLUMN_ROWID
            + " integer primary key autoincrement, " + COLUMN_FOODID
            + " text not null, " + COLUMN_NAME + " text not null, "
            + COLUMN_DAYSLEFT + " integer not null, " + COLUMN_TIPS
            + " text not null);";

    private SQLiteDatabase mDb;

    @Override
    public boolean onCreate() {
        mDb = new DatabaseHelper(getContext()).getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings2, String s2) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
