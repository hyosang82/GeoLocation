package com.geolocation.fcm;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.util.Log;

import com.geolocation.Define;

/**
 * Created by hyosang on 2017. 2. 10..
 */

public class MessageService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(Define.TAG, "Type=" + remoteMessage.getMessageType() + ", ID=" + remoteMessage.getMessageId());

        if(remoteMessage.getNotification() != null) {
            String msgbody = remoteMessage.getNotification().getBody();

            Log.d(Define.TAG, "Message=" + msgbody);
        }
    }
}
