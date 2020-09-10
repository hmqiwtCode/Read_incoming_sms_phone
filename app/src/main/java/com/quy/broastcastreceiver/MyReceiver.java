package com.quy.broastcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (intent.getAction().equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE")) {
            Toast.makeText(context, "work", Toast.LENGTH_SHORT).show();
        }
        if (intent.getAction().equalsIgnoreCase("android.provider.Telephony.SMS_RECEIVED")) {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdusObj.length];
                for (int i = 0; i < messages.length; i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = bundle.getString("format");
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
                    } else {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    }
                    String senderNum = messages[i].getOriginatingAddress();
                    String message = messages[i].getMessageBody();//
                    Toast.makeText(context, senderNum +" : " + message, Toast.LENGTH_SHORT).show();
                    Log.d("Ok",senderNum +" : " + message);
                }
            }
        }
        //Receive intent from Service  to make a Toast or anything you like.
        if (intent.getAction().equalsIgnoreCase("com.quy.chat")) {
            Toast.makeText(context, bundle.getString("ser")+"", Toast.LENGTH_SHORT).show();
        }

    }
}
