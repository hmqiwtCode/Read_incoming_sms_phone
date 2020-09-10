package com.quy.broastcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class BoundServiceU extends AppCompatActivity {
    private MyRealService myRealService;
    private boolean isBound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service_u);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this,MyRealService.class);
        // bindService will send myServiceConn to MyRealService.class, then onBind in class MyRealService.class will involve
        // and return HoldBind(extends IBinder) in onServiceConnected callback method. It means iBinder = holdBind is one
        bindService(intent,myServiceConn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (isBound) {
            unbindService(myServiceConn);
            isBound = false;
        }
    }
    public void methodTrigger(View v) {
        if (isBound) {
            int number = myRealService.addTwoNumber(10,20);
            Toast.makeText(this, "number: " + number, Toast.LENGTH_SHORT).show();
        }
    }


    private ServiceConnection myServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // At here we will get class MyRealService to using methods (addTwoNumber....)
            MyRealService.HoldBind bind = (MyRealService.HoldBind) iBinder;
            myRealService = bind.getMyRealService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };
}