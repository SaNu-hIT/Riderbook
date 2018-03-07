package com.example.angeo.newproject.PoolModule;

import android.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class MyLocationService extends Service {

    Handler mHandler;
    long INTERVAL = 3600000;//1min=60000, 1 hour = 3600000
    URL url;
    String parameter;
    public static Location LOCATION;
    private static final String TAG = "BOOMBOOMTESTGPS";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 5000;
    private static final float LOCATION_DISTANCE = 0;
    //public static final String MY_LOCATION_SERVICE = "swisstime.service.MY_LOCATION_SERVICE";
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void scheduleNext() {
        System.out.println("check service scheduleNext");
        try {

            double latitude = 0;
            double longitude = 0;
            if (mLocationManager != null) {
                isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                System.out.println("check service isGPSEnabled " + isGPSEnabled);
                System.out.println("check service isNetworkEnabled " + isNetworkEnabled);


                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Toast.makeText(MyLocationService.this, "location :: " + mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude() + "      " + mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude(), Toast.LENGTH_SHORT).show();

                    DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference("Coordinates");
                    mDatabase.child("testuser").setValue(new LatLng(mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude(),mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude()));
                }
                else
                {
                    // can't get location
                    System.out.println("check Can't get location ");
//                Toast.makeText(getApplicationContext(), "Can't get location! Please enable GPS!", Toast.LENGTH_LONG).show();
                    mHandler.postDelayed(new Runnable() {
                        public void run() {
                            scheduleNext();
                        }
                    }, 0);
                }

        } catch (Exception e) {

        System.out.println("check service Exception "+e.getMessage());
        mHandler.postDelayed(new Runnable() {
            public void run() {
                scheduleNext();
            }
        }, 0);
        e.printStackTrace();
    }
    }

    public int onStartCommand(Intent intent, int x, int y) {
        System.out.println("check service onStartCommand");
        mHandler = new Handler();
        scheduleNext();
        super.onStartCommand(intent, x, y);
        return START_STICKY;
    }


    private class LocationListener implements android.location.LocationListener {

        Location mLastLocation;

        public LocationListener(String provider) {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location) {
            Log.e(TAG, "onLocationChanged: " + location);
            //mLastLocation.set(location);
            mLastLocation = location;
            LOCATION = location;

            Toast.makeText(MyLocationService.this, "location :: "+location.getLatitude()+"      "+location.getLongitude(), Toast.LENGTH_SHORT).show();

            DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference("Coordinates");
            mDatabase.child("user").push().setValue(new LatLng(location.getLatitude(),location.getLongitude()));
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.e(TAG, "onProviderDisabled: " + provider);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e(TAG, "onStatusChanged: " + provider);
        }
    }

    LocationListener[] mLocationListeners = new LocationListener[]{
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        initializeLocationManager();
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }

}
