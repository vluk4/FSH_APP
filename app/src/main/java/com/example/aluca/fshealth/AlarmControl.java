package com.example.aluca.fshealth;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlarmControl extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent){
        String nome = intent.getStringExtra("nome");
        createNofitication(context, nome);
    }

    public void createNofitication(Context context, String nome) {
        final int id = (int) System.currentTimeMillis();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(nome)
                .setContentTitle("TOMAR O REMÉDIO")
                .setContentInfo(nome)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }
}
