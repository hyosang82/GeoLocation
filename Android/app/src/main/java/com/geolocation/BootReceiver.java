package com.geolocation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.geolocation.fcm.IDService;

/**
 * Created by hyosang on 2017. 2. 9..
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        (new IDService()).onTokenRefresh();

        Toast.makeText(context,"APP STARTED!!!!", Toast.LENGTH_LONG).show();
        Log.d("GeoLocation", "BOOT_COMPLETED");
    }
}
