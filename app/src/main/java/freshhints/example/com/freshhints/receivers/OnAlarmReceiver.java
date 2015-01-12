package freshhints.example.com.freshhints.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.util.Log;

import freshhints.example.com.freshhints.Services.WakeReminderIntentService;

/**
 * Created by anniedevine on 1/9/15.
 */
public class OnAlarmReceiver extends BroadcastReceiver {

    private static final String TAG = ComponentInfo.class.getCanonicalName();


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Received wake up from alarm manager.");

        long rowid = intent.getExtras().getLong(FoodItemsDbAdapter.KEY_ROWID);

        WakeReminderIntentService.acquireStaticLock(context);

        Intent i = new Intent(context, ReminderService.class);
        i.putExtra(FoodItemsDbAdapter.KEY_ROWID, rowid);
        context.startService(i);

    }
}