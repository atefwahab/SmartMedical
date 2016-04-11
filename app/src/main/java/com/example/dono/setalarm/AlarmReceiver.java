package com.example.dono.setalarm;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by dono on 3/28/2016.
 */
public class AlarmReceiver extends WakefulBroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Intent service1=new Intent(context.getApplicationContext(),MyAlarmService.class);
        context.startService(service1);

    }










}
