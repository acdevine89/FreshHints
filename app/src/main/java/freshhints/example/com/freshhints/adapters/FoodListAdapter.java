package freshhints.example.com.freshhints.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import freshhints.example.com.freshhints.R;
import freshhints.example.com.freshhints.models.Food;

/**
 * Created by anniedevine on 12/8/14.
 */
public class FoodListAdapter extends ArrayAdapter<Food> {

    public FoodListAdapter(Context context) {
        super(context, R.layout.fragment_view_food_row);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_view_food_row, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Food foodItem = getItem(position);

        viewHolder.nameTextView.setText(foodItem.getName());
        viewHolder.daysLeftTextView.setText(foodItem.getDaysLeft());
//        viewHolder.foodDetailsButton.setText(foodItem.getTips());

        return convertView;
    }

    private static class ViewHolder {
        private TextView nameTextView;
        private TextView daysLeftTextView;
//        private Button foodDetailsButton;
//        private Button deleteButton;

        public ViewHolder(View rootView) {
            this.nameTextView = (TextView) rootView.findViewById(R.id.name_text);
            this.daysLeftTextView = (TextView) rootView.findViewById(R.id.last_active_text);
//            this.foodDetailsButton = (Button) rootView.findViewById(R.id.food_details_button);
//            this.deleteButton = (Button) rootView.findViewById(R.id.delete_button);
        }
    }
}
