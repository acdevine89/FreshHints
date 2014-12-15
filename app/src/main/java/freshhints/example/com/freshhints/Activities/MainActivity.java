package freshhints.example.com.freshhints.activities;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import freshhints.example.com.freshhints.fragments.MainMenuFragment;
import freshhints.example.com.freshhints.R;
import freshhints.example.com.freshhints.interfaces.FragmentController;


public class MainActivity extends FragmentActivity implements FragmentController {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

//        if (fragment == null) {
            fragment = new MainMenuFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //change action_settings to home? or food list?
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void swapFragment(Fragment targetFragment) {
         getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, targetFragment)
                    .addToBackStack(null)
                    .commit();
    }
}
