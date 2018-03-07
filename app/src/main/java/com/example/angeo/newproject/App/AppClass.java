package com.example.angeo.newproject.App;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;

import com.example.angeo.newproject.PoolModule.MyLocationService;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Kichu on 21-01-2018.
 */

public class AppClass extends Application {


    Activity activity;
    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);


        if (!isServiceRunning(MyLocationService.class))
        {

                startService(new Intent(this, MyLocationService.class));
                //startService(new Intent(MyLocationService.MY_LOCATION_SERVICE));

        }

    }

    private boolean isServiceRunning(Class<?> serviceClass){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        // Loop through the running services
        for(ActivityManager.RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                // If the service is running then return true
                return true;
            }
        }
        return false;
    }
}
