package com.geolocation.datastore;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by hyosang on 2017. 2. 10..
 */
@Entity
public class FcmToken {
    public static final String KIND = "FcmToken";

    @Id public String lineNo;
    public String token;

    public FcmToken() {

    }

    public FcmToken(String lineno, String tkn) {
        this();

        lineNo = lineno;
        this.token = tkn;
    }
}
