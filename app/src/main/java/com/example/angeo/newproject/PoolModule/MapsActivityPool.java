package com.example.angeo.newproject.PoolModule;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.angeo.newproject.GPSTracker;
import com.example.angeo.newproject.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MapsActivityPool extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    GPSTracker gps;
    GoogleApiClient mGoogleApiClient;
    double lati,longi;
    protected Location mCurrentLocation_DB;
    private FusedLocationProviderClient mFusedLocationClient;
    DatabaseReference db_referrence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_pool);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

          }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            return;
        }

        db_referrence.getRoot().child("images")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {




                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        System.out.println("LOCATION11111  : "+mFusedLocationClient.getLastLocation());
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        System.out.println("LOCATION1234  : "+location);


                        lati=location.getLatitude();
                        longi=location.getLongitude();

                        mCurrentLocation_DB=location;

                        if (mCurrentLocation_DB != null) {


                            System.out.println("LOCATION123  : "+location);

                            mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(lati,longi)) //setting position
                                    .draggable(true) //Making the marker draggable
                                    .title("Current LocationClass")); //Adding a title

//        Toast.makeText(this, "CHANGING LOCATION : "+mCurrentLocation_DB.getLatitude()+" "+mCurrentLocation_DB.getLongitude(), Toast.LENGTH_SHORT).show();

                            //Moving the camera
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lati,longi)));

                            //Animating the camera
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));


                            buildGoogleApiClient();
                            if (ActivityCompat.checkSelfPermission(MapsActivityPool.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivityPool.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                                return;
                            }

                            mMap.setMyLocationEnabled(true);
                            Toast.makeText(MapsActivityPool.this, "LocationClass "+mCurrentLocation_DB.getLatitude()+" longitude  : "+mCurrentLocation_DB.getLongitude(), Toast.LENGTH_SHORT).show();
                            // Logic to handle location object
                        }else {

                            System.out.println("LOCATION  : "+location);

                        }
                    }
                });
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
