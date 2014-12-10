package freshhints.example.com.freshhints.fragments;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import freshhints.example.com.freshhints.interfaces.FragmentController;

/**
 * Created by anniedevine on 12/9/14.
 */
public class BaseFragment extends Fragment {

    private FragmentController fc;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (getActivity() instanceof FragmentController) {
            fc = ((FragmentController) getActivity());
        } else {
            throw new IllegalArgumentException("Your activity must implement the FragmentController interface");
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

}