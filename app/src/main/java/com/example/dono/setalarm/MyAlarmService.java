package com.example.dono.setalarm;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.enghany.androidproject.MainActivity;

/**
 * Created by dono on 4/2/2016.
 */
public class MyAlarmService extends Service {
    private Context context;
    boolean flag;
    private NotificationManager mManager;




    @Override
    public void onCreate() {
        super.onCreate();
        flag =true;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        System.out.println("destroy");
    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        if (flag ) {
            System.out.println("onReceiver");


           MainActivity inst = MainActivity.instance();

          //  MainActivity inst=new MainActivity();

            if (inst != null) {
                inst.StartDialog();
            }

                flag=false;

        }else{
            flag =true;
        }

    }



}
