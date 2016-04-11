package com.example.dono.setalarm;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dono.listView.Main2Activity;
import com.example.enghany.androidproject.MainActivity;
import com.example.enghany.androidproject.R;

public class RemindActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        final Ringtone ringtone = RingtoneManager.getRingtone(this.getApplicationContext(), alarmUri);
        ringtone.play();


        Button accept = (Button) findViewById(R.id.btn_yes);
        Button cancel = (Button) findViewById(R.id.btn_no);


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // a7ot array hategy men database feha el adweya elly fe nafs el m3ad hena w fe cancle bardo

                ringtone.stop();
                
                Intent myIntent = new Intent(MainActivity.instance(), Main2Activity.class);
                MainActivity.instance().startActivity(myIntent);

                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringtone.stop();
                Intent intent = new Intent(MainActivity.instance() ,Main2Activity.class);
                PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                Notification notifi = new Notification.Builder(getApplicationContext())
                        .setTicker("Ticker Title")
                        .setContentTitle("Notification  Title")
                        .setContentText("Notification content.")
                        .setSmallIcon(R.drawable.addicon)
                        .setContentIntent(pIntent).getNotification();
                notifi.flags=Notification.FLAG_AUTO_CANCEL;
                NotificationManager notifiManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notifiManager.notify(0, notifi);

                finish();
            }
        });


    }

}
