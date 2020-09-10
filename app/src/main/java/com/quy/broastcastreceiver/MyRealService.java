package com.quy.broastcastreceiver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyRealService extends Service {

    HoldBind holdBind = new HoldBind();
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return holdBind;
    }


    //Writes public method for Client using (on Activity)
    public int addTwoNumber(int a, int b){
        return a + b;
    }
    //.....


    // Purpose to return this class for Activity
    class HoldBind extends Binder {
        MyRealService getMyRealService(){
            return MyRealService.this;
        }

    }
}
