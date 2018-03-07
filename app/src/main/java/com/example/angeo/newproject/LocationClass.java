package com.example.angeo.newproject;

/**
 * Created by Codmob on 30-01-17.
 */

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class LocationClass {

    public Double Latitude;
    public Double Longitude;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public LocationClass() {
    }

    public LocationClass(Double Latitude, Double Longitude) {
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }
}