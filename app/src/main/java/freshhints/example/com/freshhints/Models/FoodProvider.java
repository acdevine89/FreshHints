package freshhints.example.com.freshhints.models;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * Created by anniedevine on 1/5/15.
 */
public class FoodProvider extends ContentProvider {

    public static String AUTHORITY = "freshhints.example.com.freshhints.models.FoodProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/food");

    // MIME types used for searching words or looking up a single definition
    public static final String FOODS_MIME_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/vnd.freshhints.example.com.freshhints.models.FoodProvider.food";
    public static final String FOOD_MIME_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/vnd.freshhints.example.com.freshhints.models.FoodProvider.food";

    // Database Columns
    public static final String COLUMN_ROWID = "_id";
    //public static final String COLUMN_FOODID = "food_id";
    //public static final String COLUMN_DATE = "reminder_date";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DAYSLEFT = "days_left";
    public static final String COLUMN_TIPS = "tips";

    // Database Related Constants
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "food_data";
    private static final String DATABASE_TABLE = "food_table";

    private static final String DATABASE_CREATE = "create table "
            + DATABASE_TABLE + " (" + COLUMN_ROWID
            + " integer primary key autoincrement, " + COLUMN_NAME + " text not null, "
            + COLUMN_DAYSLEFT + " integer not null, " + COLUMN_TIPS
            + " text not null);";

    private static final int LIST_FOOD = 0;
    private static final int ITEM_FOOD = 1;
    private static final UriMatcher sURIMatcher = buildUriMatcher();

    private SQLiteDatabase mDb;

    // Builds up a UriMatcher for search suggestion and shortcut refresh queries
    private static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, "food", LIST_FOOD);
        matcher.addURI(AUTHORITY, "food/#", ITEM_FOOD);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDb = new DatabaseHelper(getContext()).getWritableDatabase();
        return true;
    }

// Implementation of SQLiteOpenHelper
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            // Call made to the base SQLiteOpenHelper constructor. This call creates,
            // opens, and/or manages a database, which isn't created or opened until
            // getReadableDatabase() or getWriteableDatabase() is called on the
            //SQLiteOpenHelper instance
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            throw new UnsupportedOperationException();
        }
    }



    @Override
    public Cursor query(Uri uri, String[] ignored1, String ignored2,
                        String[] ignored3, String ignored4) {

        String[] projection = new String[] { FoodProvider.COLUMN_ROWID,
        FoodProvider.COLUMN_NAME, FoodProvider.COLUMN_DAYSLEFT, FoodProvider.COLUMN_TIPS };

        // Use the UriMatcher to see the query type and format the db query accordingly
        Cursor c;
        switch (sURIMatcher.match(uri)) {
            case LIST_FOOD:
                c = mDb.query(FoodProvider.DATABASE_TABLE, projection,
                        FoodProvider.COLUMN_ROWID + "=?", new String[]
                        { Long.toString(ContentUris.parseId(uri)) },
                        null, null, null, null);
                if (c != null && c.getCount() > 0) {
                    c.moveToFirst();
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }

        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        switch (sURIMatcher.match(uri)) {
            case LIST_FOOD:
                return FOODS_MIME_TYPE;
            case ITEM_FOOD:
                return FOOD_MIME_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        contentValues.remove(FoodProvider.COLUMN_ROWID);
        long id = mDb.insertOrThrow(FoodProvider.DATABASE_TABLE, null, contentValues);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String ignored1, String[] ignored2) {
        int count = mDb.delete(FoodProvider.DATABASE_TABLE, FoodProvider.COLUMN_ROWID + "=?",
                new String[] { Long.toString(ContentUris.parseId(uri)) });
        if (count > 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
