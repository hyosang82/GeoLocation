package com.geolocation;

import static java.lang.Double.NaN;

/**
 * Created by hyosang on 2017. 2. 10..
 */
public class Common {
    public static boolean isEmpty(String s) {
        if((s != null) && (s.length() > 0)) {
            return false;
        }

        return true;
    }

    public static double parseDouble(String v) {
        try {
            return Double.parseDouble(v);
        }catch(NumberFormatException e) {
            return NaN;
        }
    }
}
