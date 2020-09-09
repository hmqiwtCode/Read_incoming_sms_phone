package com.quy.broastcastreceiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyReceiver myReceiver = new MyReceiver();
    public static TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        isCheckPermission();
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(myReceiver,intentFilter);
    }

    public void sendBroadcast(View view){
        Intent intent = new Intent();
        intent.setAction("android.provider.Telephony.SMS_RECEIVED");
        intent.putExtra("msg","Chay khong vay");
        intent.setComponent(new ComponentName(getPackageName(),"com.quy.broastcastreceiver.MyReceiver"));
        getApplicationContext().sendBroadcast(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myReceiver);
    }

    private void isCheckPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},1);
        else{

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }else{
                Toast.makeText(this, "Not ok", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Not ok", Toast.LENGTH_SHORT).show();
        }
    }
}