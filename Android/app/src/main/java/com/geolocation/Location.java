package com.geolocation;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

/**
 * Created by hyosang on 2017. 2. 10..
 */

public class Location {
    public static String getBestProvider(LocationManager manager) {
        List<String> providers = manager.getAllProviders();

        boolean isGps = false;
        boolean isNetwork = false;

        for(String p : providers) {
            if(LocationManager.GPS_PROVIDER.equals(p)) {
                isGps = true;
            }else if(LocationManager.NETWORK_PROVIDER.equals(p)) {
                isNetwork = true;
            }
        }

        if(isGps) {
            return LocationManager.GPS_PROVIDER;
        }else if(isNetwork) {
            return LocationManager.NETWORK_PROVIDER;
        }else {
            return null;
        }
    }

    public static LocationListener locationUpdated = new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
            Log.d(Define.TAG, "LocationUpdated = " + location.getLongitude() + " / " + location.getLatitude());
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
