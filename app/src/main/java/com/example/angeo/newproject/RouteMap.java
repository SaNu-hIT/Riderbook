package com.example.angeo.newproject;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.ahmadrosid.lib.drawroutemap.DrawMarker;
import com.ahmadrosid.lib.drawroutemap.DrawRouteMaps;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Created by techxform on 04-Mar-18.
 */

public class RouteMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String StartLati,StartLongi,DestLati,DestiLongi;
    String Startname,Destination;
    TextView StartPlace,DestinationPlace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        StartPlace=(TextView)findViewById(R.id.starting);
        DestinationPlace=(TextView)findViewById(R.id.destination);

        Bundle n=getIntent().getExtras();
        StartLati=n.getString("StartLati","");
        StartLongi=n.getString("StartLongi","");
        DestLati=n.getString("DestLati","");
        DestiLongi=n.getString("DestiLongi","");
        Startname=n.getString("StartLocation","");
        Destination=n.getString("Destination","");

        StartPlace.setText(Startname);
        DestinationPlace.setText(Destination);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double StartLati1,StartLongi1,DestLati1,DestiLongi1;
        StartLati1= Double.parseDouble((StartLati));
        StartLongi1= Double.parseDouble(StartLongi);
        DestLati1= Double.parseDouble(DestLati);
        DestiLongi1=Double.parseDouble (DestiLongi);
        LatLng origin = new LatLng(StartLati1, StartLongi1);
        LatLng destination = new LatLng(DestLati1, DestiLongi1);
        DrawRouteMaps.getInstance(this)
                .draw(origin, destination, mMap);
        DrawMarker.getInstance(this).draw(mMap, origin, R.drawable.marker_a, Startname);
        DrawMarker.getInstance(this).draw(mMap, destination, R.drawable.marker_b, Destination);

        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(origin)
                .include(destination).build();
        Point displaySize = new Point();
        getWindowManager().getDefaultDisplay().getSize(displaySize);
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, displaySize.x, 250, 30));
    }
}