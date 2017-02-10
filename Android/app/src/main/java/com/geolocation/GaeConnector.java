package com.geolocation;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hyosang on 2017. 2. 10..
 */

public class GaeConnector {
    private static final String HOST = "http://geolocation-11.appspot.com";
    private static final String URI_REGISTER = "/register";
    private static final String URI_REPORT = "/report";

    public static void register(final String lineNo, final String token) {
        (new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(HOST + URI_REGISTER);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(false);
                    conn.setDoInput(false);
                    conn.setRequestMethod("POST");
                    conn.addRequestProperty("LineNo", lineNo);
                    conn.addRequestProperty("Token", token);
                    conn.connect();

                    int resp = conn.getResponseCode();

                    Log.d(Define.TAG, "Register Response=" + resp);
                } catch (IOException e) {
                    Log.e(Define.TAG, Log.getStackTraceString(e));
                }
            }
        }).start();
    }

    public static void report(final String lineNo, final String lng, final String lat) {
        (new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(HOST + URI_REPORT);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(false);
                    conn.setDoInput(false);
                    conn.setRequestMethod("POST");
                    conn.addRequestProperty("LineNo", lineNo);
                    conn.addRequestProperty("Lng", lng);
                    conn.addRequestProperty("Lat", lat);
                    conn.connect();

                    int resp = conn.getResponseCode();

                    Log.d(Define.TAG, "Report response = " + resp);

                }catch(IOException e) {
                    Log.e(Define.TAG, Log.getStackTraceString(e));
                }
            }
        }).start();
    }
}
