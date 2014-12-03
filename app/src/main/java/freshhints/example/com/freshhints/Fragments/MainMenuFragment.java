package freshhints.example.com.freshhints.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import freshhints.example.com.freshhints.R;
import freshhints.example.com.freshhints.interfaces.FragmentController;

/**
 * Created by anniedevine on 12/2/14.
 */
public class MainMenuFragment extends Fragment implements View.OnClickListener {

    private Button addFoodButton;
    private Button viewFoodButton;
    private FragmentController fc;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (getActivity() instanceof FragmentController) {
            fc = ((FragmentController) getActivity());
        } else {
            // throw exception
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_menu, container, false);

        addFoodButton = (Button) v.findViewById(R.id.add_food_button);
        viewFoodButton = (Button) v.findViewById(R.id.view_food_button);

        FragmentManager fm = getFragmentManager();
        final Fragment newFragment = fm.findFragmentById(R.id.fragmentContainer);

        addFoodButton.setOnClickListener(this);

        viewFoodButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.add_food_button:
                Fragment addFragment = new AddFoodFragment();
                if (fc != null) {
                    fc.swapFragment(addFragment);
                }
                break;
                //add else to throw exception
            case R.id.view_food_button:
                Fragment viewFragment = new ViewFoodFragment();
                if (getActivity() instanceof FragmentController) {
                    ((FragmentController) getActivity()).swapFragment(viewFragment);
                }
                break;
            default:
        }

    }
}
