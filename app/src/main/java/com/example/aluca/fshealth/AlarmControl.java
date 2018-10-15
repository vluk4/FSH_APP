package com.example.aluca.fshealth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

public class AlarmControl extends BroadcastReceiver {


    public void onReceive(Context context, Intent intent){
        MediaPlayer mediaPlayer = MediaPlayer.create(context,Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();
    }
}
