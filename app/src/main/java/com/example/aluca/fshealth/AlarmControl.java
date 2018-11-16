package com.example.aluca.fshealth;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class AlarmControl extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent){
        createNofitication(context);


    }
    public void createNofitication( Context context){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setDefaults(Notification.DEFAULT_ALL).setSmallIcon(R.mipmap.ic_launcher).setContentText("TESTE").setContentTitle("TESTE").setContentInfo("TESTE").setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }
}
