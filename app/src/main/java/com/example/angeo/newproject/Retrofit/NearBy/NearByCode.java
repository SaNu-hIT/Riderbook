
package com.example.angeo.newproject.Retrofit.NearBy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NearByCode {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("userid")
    @Expose
    private String userid;

    /**
     * No args constructor for use in serialization
     * 
     */
    public NearByCode() {
    }

    /**
     * 
     * @param name
     * @param userid
     * @param longitude
     * @param latitude
     * @param contact
     */
    public NearByCode(String name, String contact, String latitude, String longitude, String userid) {
        super();
        this.name = name;
        this.contact = contact;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

}
