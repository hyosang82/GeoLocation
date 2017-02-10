package com.geolocation.fcm;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import android.util.Log;

import com.geolocation.Common;
import com.geolocation.Define;
import com.geolocation.GaeConnector;

/**
 * Created by hyosang on 2017. 2. 10..
 */

public class IDService extends FirebaseInstanceIdService {
    private static String mToken = "";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.i(Define.TAG, "Token: " + token);

        mToken = token;

        register(token);
    }

    private void register(String token) {
        GaeConnector.register(Common.line1Number, token);
    }
}
