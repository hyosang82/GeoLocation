package com.geolocation;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hyosang on 2017. 2. 10..
 */

public class ReportService extends Service {
    private String provider = LocationManager.GPS_PROVIDER;

    private Timer mTimer = new Timer();

    @Override
    public void onCreate() {
        super.onCreate();

        provider = Location.getBestProvider((LocationManager) getSystemService(Context.LOCATION_SERVICE));

        if(provider != null) {
            reportTask.run();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private TimerTask reportTask = new TimerTask() {
        @Override
        public void run() {
            if(provider != null) {
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                locationManager.requestSingleUpdate(provider, locationListener, Looper.getMainLooper());
            }
        }
    };

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
            String lng = String.format("%.6f", location.getLongitude());
            String lat = String.format("%.6f", location.getLatitude());
            GaeConnector.report(Common.line1Number, lng, lat);

            mTimer.schedule(reportTask, 10000);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };


}
