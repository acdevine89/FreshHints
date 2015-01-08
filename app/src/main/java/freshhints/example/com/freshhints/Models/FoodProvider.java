package freshhints.example.com.freshhints.models;

import android.content.ContentProvider;
import android.content.ContentResolver;
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

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
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

    public static String AUTHORITY = "freshhints.example.com.freshhints.models.FoodProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/food");

    public static final String FOODS_MIME_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/vnd.freshhints.example.com.freshhints.models.FoodProvider.food_data";
    public static final String FOOD_MIME_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/vnd.freshhints.example.com.freshhints.models.FoodProvider.food_data";

    private static final int LIST_FOOD = 0;
    private static final int ITEM_FOOD = 1;
    private static final UriMatcher sURIMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, "food", LIST_FOOD);
        matcher.addURI(AUTHORITY, "food/#", ITEM_FOOD);
        return matcher;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings2, String s2) {
        return null;
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
