package com.geolocation;

import android.app.Application;

/**
 * Created by hyosang on 2017. 2. 10..
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Common.updateLine1Number(getApplicationContext());
    }
}
