package br.dpacanhella.miguelopolis.data.api;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by infra on 22/06/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService{

    private static final String TAG = "NOTICIAS";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

    String from = remoteMessage.getFrom();
        Log.d(TAG, "Message recebido de: " + from);

        if(remoteMessage.getNotification() != null){
            Log.d(TAG, "Notificação: " + remoteMessage.getNotification().getBody());
        }
    }
}
