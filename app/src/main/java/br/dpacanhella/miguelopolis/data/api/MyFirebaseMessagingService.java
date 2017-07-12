package br.dpacanhella.miguelopolis.data.api;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import br.dpacanhella.miguelopolis.FarmaciaActivity;
import br.dpacanhella.miguelopolis.R;
import br.dpacanhella.miguelopolis.UtilitarioActivity;

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

        final String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token: " + token);

        if(remoteMessage.getNotification() != null){
            Log.d(TAG, "Notificação: " + remoteMessage.getNotification().getBody());

            mostrarNotification(remoteMessage);
        }

        if(remoteMessage.getData().size() > 0){
            Log.d(TAG, "Data: " + remoteMessage.getData());

            mostrarNotification(remoteMessage);
        }
    }

    //Notificação
    private void mostrarNotification(RemoteMessage remoteMessage) {

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(this, UtilitarioActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder noti = new NotificationCompat.Builder(this)
                .setTicker("Guia Miguelópolis")
                .setContentTitle(remoteMessage.getData().get("Titulo"))
                .setContentText(remoteMessage.getData().get("Descricao"))
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.icon_medicamentos)
                .setLargeIcon(((BitmapDrawable) getApplicationContext().getResources().getDrawable(R.mipmap.icon_medicamentos)).getBitmap())
                .setSound(alarmSound)
                .setVibrate(new long[]{100, 500, 100, 500})
                .setContentIntent(pendingIntent);


        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, noti.build());
    }



}
