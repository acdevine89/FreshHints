package freshhints.example.com.freshhints;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by anniedevine on 12/2/14.
 */
public class MainMenuFragment extends Fragment {

    private Food mFood;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFood = new Food();
    }

}
