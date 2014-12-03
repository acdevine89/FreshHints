package freshhints.example.com.freshhints.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import freshhints.example.com.freshhints.R;

/**
 * Created by anniedevine on 12/2/14.
 */
public class MainMenuFragment extends Fragment {

    private Button addFoodButton;
    private Button viewFoodButton;
    FragmentManager fm = getFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_menu, container, false);

        addFoodButton = (Button) v.findViewById(R.id.add_food_button);
        viewFoodButton = (Button) v.findViewById(R.id.view_food_button);

        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragment == null) {
                    fragment = new AddFoodFragment();
                    fm.beginTransaction()
                        .add(R.id.fragmentContainer, fragment)
                        .commit();
                }
            }
        });

        viewFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragment == null) {
                    fragment = new ViewFoodFragment();
                    fm.beginTransaction()
                         .add(R.id.fragmentContainer, fragment)
                         .commit();
                }
            }
        });

        return v;
    }
}
