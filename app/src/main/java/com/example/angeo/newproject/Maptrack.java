package com.example.angeo.newproject;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class Maptrack extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String email;
    DatabaseReference locations;
    Double lat,lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maptrack);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locations= FirebaseDatabase.getInstance().getReference("LocationClass");



    }

    @Override
    protected void onResume() {
        super.onResume();



    }

    private void LoadlocationForthisUser(final String email, final Double lati, final Double langi){

        System.out.println("SHANIL 12 :  "+email);
        Query user_location=locations.orderByChild("email").equalTo(email);
        System.out.println("SHANIL user_location :  "+user_location);
        user_location.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapShot:dataSnapshot.getChildren()){

                    Tracking tracking=postSnapShot.getValue(Tracking.class);

                    LatLng frndslocation=new LatLng(Double.parseDouble(tracking.getLat()),Double.parseDouble(tracking.getLang()));

                    System.out.println("SHANIL 11 :  "+frndslocation);


                    Location currentuser=new Location("");
                    currentuser.setLatitude(lati);
                    currentuser.setLongitude(langi);

                    Location friend=new Location("");
                    friend.setLatitude(Double.parseDouble(tracking.getLat()));
                    friend.setLatitude(Double.parseDouble(tracking.getLang()));



                    mMap.addMarker(new MarkerOptions()
                                    .position(frndslocation).title(tracking.getEmail()).snippet("Distance "+new DecimalFormat("#.#").format(distance(currentuser,friend)))
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati, langi),12.0f));

                }

                LatLng current=new LatLng(lati, langi);
                mMap.addMarker(new MarkerOptions().position(current).title(FirebaseAuth.getInstance().getCurrentUser().getEmail()));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                System.out.println("SHANIL 13 :  "+databaseError);

            }
        });

        System.out.println("SHANIL 14 :  ");

    }

    private double distance(Location currentuser, Location friend) {

        double theta=currentuser.getLongitude()-friend.getLatitude();
        double dist=Math.sin(deg2rad(currentuser.getLatitude()))
                *Math.sin(deg2rad(friend.getLatitude()))
                *Math.cos(deg2rad(currentuser.getLatitude()))
                *Math.cos(deg2rad(friend.getLatitude()))
                *Math.cos(deg2rad(theta));

        dist=Math.acos(dist);
        dist=rad2deg(dist);
        dist=dist*60*1515;
        return dist;

    }

    private double rad2deg(double rad) {

        return (rad*180/Math.PI);

    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);


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

        if (getIntent()!=null){

            Bundle bn=getIntent().getExtras();
            email=bn.getString("email","");
            lat= (bn.getDouble("lat",0));
            lang= (bn.getDouble("lang",0));

            System.out.println("SHNAIL :  "+email);
            System.out.println("SHNAIL lat :  "+lat);
            System.out.println("SHNAIL lang:  "+lang);
            LoadlocationForthisUser(email,lat,lang);
//            if (!TextUtils.isEmpty(email)){
//
//
//
//            }

        }

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(lat, lang);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lang),15.0f));

    }
}
