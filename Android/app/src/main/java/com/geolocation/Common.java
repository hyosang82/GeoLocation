package com.geolocation;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by hyosang on 2017. 2. 10..
 */

public class Common {
    public static String line1Number = "";

    public static void updateLine1Number(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String no = telephonyManager.getLine1Number();
        if(isEmpty(no)) {
            no = pref.getString("line_no", "UNREG_" + generateRandomNumber());
        }else {
            no = no.replaceAll("[^0-9]", "");
        }

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("line_no", no);
        editor.commit();

        line1Number = no;
    }

    public static String generateRandomNumber() {
        return String.valueOf((int)Math.floor(Math.random() * Math.pow(10f, 8f)));
    }

    public static boolean isEmpty(String s) {
        if ((s != null) && (s.length() > 0)) {
            return false;
        }else {
            return true;
        }
    }
}
