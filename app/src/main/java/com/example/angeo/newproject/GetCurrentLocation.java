package com.example.angeo.newproject;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by user on 4/21/2017.
 */

public class GetCurrentLocation extends Activity implements LocationListener {
    String longitude;
    String latitude;
    String cityName;

    public void onLocationChanged(Location loc) {
        //editLocation.setText("");
        //pb.setVisibility(View.INVISIBLE);
//        Toast.makeText(
//                getBaseContext(),
//                "LocationClass changed: Lat: " + loc.getLatitude() + " Lng: "
//                        + loc.getLongitude(), Toast.LENGTH_SHORT).show();
//        longitude = "Longitude: " + loc.getLongitude();
//        //Log.v(TAG, longitude);
//        latitude = "Latitude: " + loc.getLatitude();
//        //Log.v(TAG, latitude);
//
//        /*------- To get city name from coordinates -------- */
//        //String cityName = null;
//        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
//        List<Address> addresses;
//        try {
//            addresses = gcd.getFromLocation(loc.getLatitude(),
//                    loc.getLongitude(), 1);
//            if (addresses.size() > 0)
//                System.out.println(addresses.get(0).getLocality());
//            cityName = addresses.get(0).getLocality();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }

        //editLocation.setText(s);
    }

    //@Override
    public void onProviderDisabled(String provider) {}

    //@Override
    public void onProviderEnabled(String provider) {}

    //@Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}


}