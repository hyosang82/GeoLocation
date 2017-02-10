package com.geolocation.datastore;

import com.geolocation.datastore.FcmToken;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

/**
 * Created by hyosang on 2017. 2. 10..
 */
@Entity
public class LocationLog {
    @Parent Key<FcmToken> token;
    @Id long timestamp;
    double longitude;
    double latitude;

    public LocationLog() {

    }

    public LocationLog(String lineNo, long ts, double lng, double lat) {
        this();

        token = Key.create(FcmToken.class, lineNo);
        timestamp = ts;
        longitude = lng;
        latitude = lat;
    }
}
