package com.example.mrkoppy.myappfyp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class AlertRing extends BroadcastReceiver {

    private MediaPlayer mediaPlayer;

    @Override
    public void onReceive(final Context context, Intent intent) {
        Toast.makeText(context, "Your trip is coming", Toast.LENGTH_LONG).show();
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        final Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(),notification);
        r.play();

        /*Ring for 5 seconds*/
        long ringDelay = 5000;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                r.stop();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, ringDelay);


    }

}
