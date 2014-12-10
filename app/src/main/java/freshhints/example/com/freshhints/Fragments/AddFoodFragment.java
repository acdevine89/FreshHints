package freshhints.example.com.freshhints.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import freshhints.example.com.freshhints.R;
import freshhints.example.com.freshhints.models.Food;

/**
 * Created by anniedevine on 12/3/14.
 */
public class AddFoodFragment extends BaseFragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_food, container, false);

//        Button submitFoodButton = (Button) getView().findViewById(R.id.submit_food_button);
//        submitFoodButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        //Food submittedFood = new Food();
        //submittedFood.setName(submittedFoodText.getText().toString());

    }
}
