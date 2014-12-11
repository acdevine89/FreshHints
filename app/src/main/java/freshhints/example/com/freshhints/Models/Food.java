package freshhints.example.com.freshhints.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anniedevine on 12/2/14.
 */
public class Food implements Parcelable {

    public static List<Food> parseJSONObject(JSONObject jsonObject) {

        try {
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray childrenArray = dataObject.getJSONArray("children");

            List<Food> foodItems = new ArrayList<Food>();

            for (int index = 0; index < childrenArray.length(); index++) {
                JSONObject childEntry = childrenArray.getJSONObject(index);

                // Wants daysLeft and tips parameters to be passed in as well
                Food newFoodItem = new Food(childEntry);
                foodItems.add(newFoodItem);
            }

            return foodItems;
        }

        catch (JSONException e) {
            return new ArrayList<Food>();
        }
    }

    private int foodID;
    private String name;
    private int daysLeft;
    private String tips;

    private Food(JSONObject jsonObject) throws JSONException {

        JSONObject foodData = jsonObject.getJSONObject("data");

        foodID = foodData.optInt("food ID", -1);
        name = foodData.optString("name", "Unknown Name");
        daysLeft = foodData.optInt("days left", -1);
        tips = foodData.optString("tips", "No tips to show");

    }

    public Food() {

    }

    public Food(int foodID, String name, int daysLeft, String tips) {
        this.foodID = foodID;
        this.name = name;
        this.daysLeft = daysLeft;
        this.tips = tips;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.foodID);
        dest.writeString(this.name);
        dest.writeInt(this.daysLeft);
        dest.writeString(this.tips);
    }

    private Food(Parcel in) {
        this.foodID = in.readInt();
        this.name = in.readString();
        this.daysLeft = in.readInt();
        this.tips = in.readString();
    }

    public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {
        public Food createFromParcel(Parcel source) {
            return new Food(source);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }

    };

}
