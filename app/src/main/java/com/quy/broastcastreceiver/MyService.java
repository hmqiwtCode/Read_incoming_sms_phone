package com.quy.broastcastreceiver;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;

import androidx.annotation.Nullable;

public class MyService extends IntentService {
    
    public MyService() {
        super("MyService");
    }

    public static boolean isRunning = false;

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // Handle in background
        //Service send intent to Receiver
        //Then receiver will display notification
        while(isRunning){
            intent.setAction("com.quy.chat");
            intent.putExtra("ser","I hope this works");
            intent.setComponent(new ComponentName(getPackageName(),"com.quy.broastcastreceiver.MyReceiver"));
            getApplicationContext().sendBroadcast(intent);

            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
