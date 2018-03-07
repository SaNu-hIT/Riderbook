package com.example.angeo.newproject;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.angeo.newproject.Adapters.AdapterGoogle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 04-Mar-18.
 */

public class ListOnline extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener {
    private List<String> name = new ArrayList<>();
    DatabaseReference onlineRef,currentUserRef,counterRef,location_db;
    FirebaseRecyclerAdapter<User,ListOnlineViewHolder>adapter;
//    RecyclerView listOnline;
    RecyclerView.LayoutManager layoutManager;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

    private static final int MY_PERMISSION_REQUEST_CODE=7171;
    private static int MY_PLAY_SERVICES_RES_REQUEST=7172;

    private RecyclerView mRecyclerView;
    private AdapterGoogle mAdapter;
    Activity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_online);

        activity=this;

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerid);
        mRecyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        location_db= FirebaseDatabase.getInstance().getReference("locations");
        onlineRef= FirebaseDatabase.getInstance().getReference().child(".info/connected");
        counterRef= FirebaseDatabase.getInstance().getReference("lastonline");
        currentUserRef= FirebaseDatabase.getInstance().getReference("lastonline")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(this,new String[]{

                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION

            },7171);


        }else {

            if (checkPlayServices()){

                buildGoogleApiClient();
                checkPlayServices();
                displayLocation();
                createLocationRequest();

            }


        }

        setupsystem();

//        Update();

    }
/*
    private void UpdateSystem() {

        adapter=new FirebaseRecyclerAdapter<User, ListOnlineViewHolder>(
                User.class,R.layout.user_layout,ListOnlineViewHolder.class,counterRef
        ) {


            @Override
            public ListOnlineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            protected void onBindViewHolder(@NonNull ListOnlineViewHolder holder, int position, @NonNull User model) {

                holder.txtemail.setText(model.getEmail());

            }
        };
        adapter.notifyDataSetChanged();
        listOnline.setAdapter(adapter);

    }*/
    private void displayLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            return;
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        System.out.println("LOCATION11111  : "+mFusedLocationClient.getLastLocation());
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {


                            if (location !=null){

                                location_db.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(new Tracking(FirebaseAuth.getInstance().getCurrentUser().
                                        getEmail(),FirebaseAuth.getInstance().getCurrentUser().getUid(),String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude())));

                                Update(location);

                            }else {

                                Toast.makeText(ListOnline.this, "Couldn't get the location", Toast.LENGTH_SHORT).show();

                            }



                            System.out.println("LOCATION  : "+location);

                            Toast.makeText(ListOnline.this, "LocationClass "+location.getLatitude()+" longitude  : "+location.getLongitude(), Toast.LENGTH_SHORT).show();
                            // Logic to handle location object
                        }else {

                            System.out.println("LOCATION  : "+location);

                        }
                    }
                });



    }

    private void createLocationRequest(){

        mLocationRequest=new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(3000);
        mLocationRequest.setSmallestDisplacement(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }


    private void buildGoogleApiClient(){

        mGoogleApiClient=new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
    }

    private boolean checkPlayServices() {

        int resultcode= GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (resultcode!= ConnectionResult.SUCCESS){

            if (GooglePlayServicesUtil.isUserRecoverableError(resultcode)){

                GooglePlayServicesUtil.getErrorDialog(resultcode,this,123).show();

            }else {

                Toast.makeText(this, "This device is not supported", Toast.LENGTH_SHORT).show();
                finish();
            }
            return false;

        }
        return true;

    }
/*
    private void UpdateSystem() {

//        adapter=new FirebaseRecyclerAdapter<User, ListOnlineViewHolder>(
//
//                User.class,
//                R.layout.user_layout,
//                ListOnlineViewHolder.class,
//                counterRef
//
//
//        ) {
//            @Override
//            public ListOnlineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                return null;
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull ListOnlineViewHolder holder, int position, @NonNull User model) {
//
//                holder.txtemail.setText(model.getEmail());
//
//            }
//
//
//        };
//
//        adapter.notifyDataSetChanged();
//        listOnline.setAdapter(adapter);





    }*/

    private void Update(Location location){

        double lati=location.getLatitude();
        double longi=location.getLongitude();
        String EmailAddress=FirebaseAuth.getInstance().getCurrentUser().getEmail();


        mRecyclerView.setVisibility(View.VISIBLE);

        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter = new AdapterGoogle(EmailAddress,lati,longi,activity, R.layout.user_layout, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

   private void setupsystem(){

       onlineRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {

               if (dataSnapshot.getValue(Boolean.class)){

                   currentUserRef.onDisconnect().removeValue();
                   counterRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(new User(FirebaseAuth.getInstance().getCurrentUser().getEmail(),"online"));
//                   adapter.notifyDataSetChanged();
                   counterRef.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {

                           for (DataSnapshot postsnapshot:dataSnapshot.getChildren()){

                               User user=postsnapshot.getValue(User.class);


                           }

                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {

                       }
                   });

               }
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });

   }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

       displayLocation();
       StartLocationUpdates();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case MY_PERMISSION_REQUEST_CODE:
            {

                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {

                    if (checkPlayServices()){

                        buildGoogleApiClient();
                        createLocationRequest();
                        displayLocation();

                    }

                }

            }


        }
    }

    private void StartLocationUpdates() {


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {

            return;
        }

//        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest, (com.google.android.gms.location.LocationListener) this);
        LocationServices.getFusedLocationProviderClient(this);

    }

    @Override
    public void onConnectionSuspended(int i) {

       mGoogleApiClient.connect();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onLocationChanged(Location location) {

       mLastLocation=location;
       displayLocation();

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mGoogleApiClient!=null){

            mGoogleApiClient.connect();

        }

    }

    @Override
    protected void onStop() {
       if (mGoogleApiClient!=null){

           mGoogleApiClient.disconnect();

       }
       super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkPlayServices();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
