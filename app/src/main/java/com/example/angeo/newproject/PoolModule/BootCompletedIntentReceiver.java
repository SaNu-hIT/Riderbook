package com.example.angeo.newproject.PoolModule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by user1 on 12/13/2017.
 */

public class BootCompletedIntentReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {

            SharedPreferences sharedPreferences1 = context.getSharedPreferences("USER", MODE_PRIVATE);
            String uservalue = sharedPreferences1.getString("username", "");
            System.out.println("Status@login: " + uservalue);
            if (!uservalue.equals(""))
            {
                Intent pushIntent = new Intent(context, MyLocationService.class);
                //Intent pushIntent = new Intent(MyLocationService.MY_LOCATION_SERVICE);
                context.startService(pushIntent);
            }
        }
    }
}
