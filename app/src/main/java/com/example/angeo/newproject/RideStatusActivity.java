package com.example.angeo.newproject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.angeo.newproject.App.ApiClient;
import com.example.angeo.newproject.App.ApiInterface;
import com.example.angeo.newproject.Retrofit.RideStatus.RideStatus;
import com.example.angeo.newproject.Retrofit.RideStatus.RideStatusModul;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.DateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

/**
 * Created by Kichu on 02-03-2018.
 */

public class RideStatusActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener,
        View.OnClickListener, LocationListener {

    protected static final String TAG = "RideStatusActivity";
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    double lati, longi;
    private FusedLocationProviderClient mFusedLocationClient;

    protected final static String KEY_REQUESTING_LOCATION_UPDATES = "requesting-location-updates";
    protected final static String KEY_LOCATION = "location";
    protected final static String KEY_LAST_UPDATED_TIME_STRING = "last-updated-time-string";
    protected GoogleApiClient mGoogleApiClient;
    protected LocationRequest mLocationRequest;
    protected LocationSettingsRequest mLocationSettingsRequest;
    protected Location mCurrentLocation;
    protected Location mCurrentLocation_DB;
    //    protected ImageButton mStartUpdatesButton;
    protected Boolean mRequestingLocationUpdates;
    protected String mLastUpdateTime;
    private Marker userMarker;
    private GoogleMap mMap;
    String sch_name, route_status, route_name;
    int i = 0, j = 0, k = 0;
    GPSTracker gps;

    private MyService myService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ridestatus);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mRequestingLocationUpdates = true;
        mLastUpdateTime = "";


        // Update values using data stored in the Bundle.
        updateValuesFromBundle(savedInstanceState);


        buildGoogleApiClient();
        createLocationRequest();
        buildLocationSettingsRequest();
        Toast.makeText(RideStatusActivity.this, "created", Toast.LENGTH_LONG).show();
    }


    /**
     * Updates fields based on data stored in the bundle.
     *
     * @param savedInstanceState The activity state saved in the Bundle.
     */
    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // Update the value of mRequestingLocationUpdates from the Bundle, and make sure that
            // the Start Updates and Stop Updates buttons are correctly enabled or disabled.
            if (savedInstanceState.keySet().contains(KEY_REQUESTING_LOCATION_UPDATES)) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean(
                        KEY_REQUESTING_LOCATION_UPDATES);
            }

            if (savedInstanceState.keySet().contains(KEY_LOCATION)) {

                mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            }

            if (savedInstanceState.keySet().contains(KEY_LAST_UPDATED_TIME_STRING)) {
                mLastUpdateTime = savedInstanceState.getString(KEY_LAST_UPDATED_TIME_STRING);
            }
            updateUI();
        }
    }

    /**
     * Builds a GoogleApiClient. Uses the {@code #addApi} method to request the
     * LocationServices API.
     */
    protected synchronized void buildGoogleApiClient() {
        Log.i(TAG, "Building GoogleApiClient");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    protected void createLocationRequest() {
        Toast.makeText(this, "createLocationRequest", Toast.LENGTH_SHORT).show();
        mLocationRequest = new LocationRequest();


        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);

        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    protected void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.i(TAG, "User agreed to make required location settings changes.");
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.i(TAG, "User chose not to make required location settings changes.");
                        mRequestingLocationUpdates = false;
                        updateUI();
                        break;
                }
                break;
        }
    }

    private MyLocationCallback myLocationCallback = new MyLocationCallback();

    /**
     * Requests location updates from the FusedLocationApi.
     */
    protected void startLocationUpdates() {
        LocationServices.SettingsApi.checkLocationSettings(
                mGoogleApiClient,
                mLocationSettingsRequest
        ).setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i(TAG, "All location settings are satisfied.");
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        LocationRequest mlocationrequest = LocationRequest.create();
                        mlocationrequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                        // mlocationrequest.setSmallestDisplacement(10);
                        mlocationrequest.setInterval(10000); // Update location every 1 minute
                        mlocationrequest.setFastestInterval(10000);
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest, myLocationCallback, null);
                        getFusedLocationProviderClient(RideStatusActivity.this).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                                    @Override
                                    public void onLocationResult(LocationResult locationResult) {
                                        // do work here
                                        onLocationChanged(locationResult.getLastLocation());
                                    }
                                },
                                Looper.myLooper());
                }


//
//                        getFusedLocationProviderClient(getApplicationContext()).requestLocationUpdates(
//                                mGoogleApiClient, mLocationRequest, RideStatusActivity.this);
//                        break;
//                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//                        Log.i(TAG, "LocationClass settings are not satisfied. Attempting to upgrade " +
//                                "location settings ");
//                        try {
//                            // Show the dialog by calling startResolutionForResult(), and check the
//                            // result in onActivityResult().
//                            status.startResolutionForResult(RideStatusActivity.this, REQUEST_CHECK_SETTINGS);
//                        } catch (IntentSender.SendIntentException e) {
//                            Log.i(TAG, "PendingIntent unable to execute request.");
//                        }
//                        break;
//                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                        String errorMessage = "LocationClass settings are inadequate, and cannot be " +
//                                "fixed here. Fix in Settings.";
//                        Log.e(TAG, errorMessage);
//                        Toast.makeText(RideStatusActivity.this, errorMessage, Toast.LENGTH_LONG).show();
//                        mRequestingLocationUpdates = false;

                updateUI();
            }
        });

    }

    private class MyLocationCallback extends com.google.android.gms.location.LocationCallback {

        public void onLocationResult(LocationResult var1) {
            Toast.makeText(RideStatusActivity.this, var1.getLastLocation().getLatitude() + "", Toast.LENGTH_SHORT).show();
        }

        public void onLocationAvailability(LocationAvailability var1) {
            Toast.makeText(RideStatusActivity.this, var1.isLocationAvailable() + "", Toast.LENGTH_SHORT).show();

        }
    }

    private void updateUI() {
        setButtonsEnabledState();
        updateLocationUI();
    }

    private void setButtonsEnabledState() {
        if (mRequestingLocationUpdates) {
//            mStartUpdatesButton.setEnabled(false);
        } else {
//            mStartUpdatesButton.setEnabled(true);
        }
    }

    private void updateLocationUI() {
        if (mCurrentLocation != null) {
        }
    }

    /**
     * Removes location updates from the FusedLocationApi.
     */
    protected void stopLocationUpdates() {

        mFusedLocationClient.removeLocationUpdates(myLocationCallback);


        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient,
                this
        ).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(Status status) {
                mRequestingLocationUpdates = false;
                setButtonsEnabledState();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onResume() {
        super.onResume();

//        PrevButton.setVisibility(View.GONE);
        if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
            startLocationUpdates();
        }
        updateUI();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle connectionHint) {

        if (mCurrentLocation == null) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling

                mFusedLocationClient= getFusedLocationProviderClient(this);

                mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {


                        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
                        updateLocationUI();
                        System.out.println("Details : " + location.getLatitude() + " " + location.getLongitude());
                        Double lati = location.getLatitude();
                        Double longi = location.getLongitude();

                        System.out.println("Details : " + lati + " " + longi);

                    }
                });





//                mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);


                return;
            }

        }
        if (mRequestingLocationUpdates) {
            Log.i(TAG, "in onConnected(), starting location updates");
            startLocationUpdates();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        UpdateUser(location.getLatitude(), location.getLongitude());

        Toast.makeText(this, "LOCATION CHANGED :   " + location.getLatitude() + "     " + location.getLongitude(), Toast.LENGTH_SHORT).show();

        updateLocationUI();

        CameraPosition camPosition = mMap.getCameraPosition();


        CameraPosition newPos = new CameraPosition(
                new LatLng(location.getLatitude(), location.getLongitude()),
                camPosition.zoom,
                camPosition.tilt, 0);

//        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(newPos));
//        userMarker.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));

        lati = location.getLatitude();
        longi = location.getLongitude();

        mCurrentLocation_DB = location;

        if (mCurrentLocation_DB != null) {


            System.out.println("LOCATION123  : " + location);

            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lati, longi)) //setting position
                    .draggable(true) //Making the marker draggable
                    .title("Current LocationClass")); //Adding a title

//        Toast.makeText(this, "CHANGING LOCATION : "+mCurrentLocation_DB.getLatitude()+" "+mCurrentLocation_DB.getLongitude(), Toast.LENGTH_SHORT).show();
            UpdateUser(lati, longi);
            //Moving the camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lati, longi)));

            //Animating the camera
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "Connection suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {

        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    /**
     * Stores activity data in the Bundle.
     */
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(KEY_REQUESTING_LOCATION_UPDATES, mRequestingLocationUpdates);
        savedInstanceState.putParcelable(KEY_LOCATION, mCurrentLocation);
        savedInstanceState.putString(KEY_LAST_UPDATED_TIME_STRING, mLastUpdateTime);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mFusedLocationClient = getFusedLocationProviderClient(this);
        System.out.println("LOCATION11111  : " + mFusedLocationClient.getLastLocation());
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        System.out.println("LOCATION1234  : " + location);


                        lati = location.getLatitude();
                        longi = location.getLongitude();

                        mCurrentLocation_DB = location;

                        if (mCurrentLocation_DB != null) {


                            System.out.println("LOCATION123  : " + location);

                            mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(lati, longi)) //setting position
                                    .draggable(true) //Making the marker draggable
                                    .title("Current LocationClass")); //Adding a title

//        Toast.makeText(this, "CHANGING LOCATION : "+mCurrentLocation_DB.getLatitude()+" "+mCurrentLocation_DB.getLongitude(), Toast.LENGTH_SHORT).show();
                            UpdateUser(lati, longi);
                            //Moving the camera
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lati, longi)));

                            //Animating the camera
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));


                            buildGoogleApiClient();
                            if (ActivityCompat.checkSelfPermission(RideStatusActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(RideStatusActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                                return;
                            }

                            mMap.setMyLocationEnabled(true);
                            Toast.makeText(RideStatusActivity.this, "LocationClass " + mCurrentLocation_DB.getLatitude() + " longitude  : " + mCurrentLocation_DB.getLongitude(), Toast.LENGTH_SHORT).show();
                            // Logic to handle location object
                        } else {

                            System.out.println("LOCATION  : " + location);

                        }
                    }
                });


//        return;
//        }


//
//


//
//
//
//        System.out.println("Directions : "+mCurrentLocation_DB.getLatitude()+"  "+mCurrentLocation_DB.getLongitude());
//

    }


    private void UpdateUser(double latitude, double longitude) {


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<RideStatusModul> call = apiService.getupdateuser("updatelocationsndfcm.php", "1", latitude, longitude, "5");

        call.enqueue(new Callback<RideStatusModul>() {
            @Override
            public void onResponse(Call<RideStatusModul> call, retrofit2.Response<RideStatusModul> response) {

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                Toast.makeText(RideStatusActivity.this, "URL  ::::::::: " + URL, Toast.LENGTH_SHORT).show();
//                ProgressBarrr.setVisibility(View.GONE);
                RideStatus registerStatus = response.body().getStatus();
                String code = registerStatus.getCode();
                System.out.println("SHANIL : 1" + registerStatus);

                if (code.equals("200")) {
                    System.out.println("SHANIL : 2");


                    Toast.makeText(RideStatusActivity.this, "Updated...", Toast.LENGTH_SHORT).show();

                } else if (code.equals("403")) {


                    Toast.makeText(RideStatusActivity.this, "phone number already used...", Toast.LENGTH_SHORT).show();
                } else if (code.equals("204")) {

                    Toast.makeText(RideStatusActivity.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();

                } else if (code.equals("402")) {

                    Toast.makeText(RideStatusActivity.this, "username already used...", Toast.LENGTH_SHORT).show();

                } else if (code.equals("500")) {

                    Toast.makeText(RideStatusActivity.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RideStatusModul> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(RideStatusActivity.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();
//                ProgressBarrr.setVisibility(View.GONE);
            }
        });

    }


}


