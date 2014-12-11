package freshhints.example.com.freshhints.models;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import freshhints.example.com.freshhints.interfaces.FoodListLoaderListener;

/**
 * Created by anniedevine on 12/10/14.
 */
public class FoodListLoader {

    private FoodListLoaderListener listener;

    public FoodListLoader(FoodListLoaderListener listener) {
        this.listener = listener;
    }

    public void getFoodItems() {
        new GetFoodItemsTask().execute();
    }

    public class GetFoodItemsTask extends AsyncTask<Void, Void, List<Food>> {

        private final String LOG_TAG = GetFoodItemsTask.class.getSimpleName();

        @Override
        protected List<Food> doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String foodItemJsonString = null;
            List<Food> foodItemList;

            try {
                final String BASE_URL = "http://private-d563a-freshhints.apiary-mock.com/notes";
                URL url = new URL(BASE_URL);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                foodItemJsonString = buffer.toString();
                Log.v(LOG_TAG, "Food JSON String: " + foodItemJsonString);

            } catch (IOException e) {
                Log.e(LOG_TAG, "Error", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                foodItemList = getFoodItemsFromJson(foodItemJsonString);

            } catch (JSONException e) {
                Log.e(LOG_TAG, "Error", e);
                return null;
            }

            return foodItemList;

        }

        private List<Food> getFoodItemsFromJson(String foodItemJsonString)
            throws JSONException {

            final String API_FRESHHINTS_FOODID = "foodID";
            final String API_FRESHHINTS_NAME = "name";
            final String API_FRESHHINTS_DAYSLEFT = "daysLeft";
            final String API_FRESHHINTS_TIPS = "tips";

            JSONArray jsonArray = new JSONArray(foodItemJsonString);
            List<Food> foodItemList = new ArrayList<Food>();

            for (int i = 0; i < jsonArray.length(); i++) {
                Food thisFoodItem = new Food();
                JSONObject jsonFoodItem = jsonArray.getJSONObject(i);

                int foodID = jsonFoodItem.optInt(API_FRESHHINTS_FOODID, -1);
                thisFoodItem.setFoodID(foodID);

                String name = jsonFoodItem.optString(API_FRESHHINTS_NAME, "no");
                thisFoodItem.setName(name);

                int daysLeft = jsonFoodItem.optInt(API_FRESHHINTS_DAYSLEFT, -1);
                thisFoodItem.setDaysLeft(daysLeft);

                String tips = jsonFoodItem.optString(API_FRESHHINTS_TIPS, "no");
                thisFoodItem.setTips(tips);

                foodItemList.add(thisFoodItem);
            }

            return foodItemList;

        }

        @Override
        protected void onPostExecute(List<Food> foodItemList) {
            if (FoodListLoader.this.listener != null) {
                FoodListLoader.this.listener.foodItemsLoaded(foodItemList);
            }
        }

    }

}
