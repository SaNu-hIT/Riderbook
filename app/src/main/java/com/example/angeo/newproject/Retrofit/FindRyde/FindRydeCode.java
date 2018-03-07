
package com.example.angeo.newproject.Retrofit.FindRyde;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FindRydeCode {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("startlat")
    @Expose
    private String startlat;
    @SerializedName("startlon")
    @Expose
    private String startlon;
    @SerializedName("destlat")
    @Expose
    private String destlat;
    @SerializedName("destlon")
    @Expose
    private String destlon;
    @SerializedName("offerdate")
    @Expose
    private String offerdate;
    @SerializedName("offertime")
    @Expose
    private String offertime;
    @SerializedName("bikemodel")
    @Expose
    private String bikemodel;
    @SerializedName("logdate")
    @Expose
    private String logdate;
    @SerializedName("logtime")
    @Expose
    private String logtime;
    @SerializedName("regno")
    @Expose
    private String regno;
    @SerializedName("seats")
    @Expose
    private String seats;
    @SerializedName("vehicle")
    @Expose
    private String vehicle;
    @SerializedName("features")
    @Expose
    private String features;
    @SerializedName("carmodel")
    @Expose
    private String carmodel;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FindRydeCode() {
    }

    /**
     * 
     * @param destlat
     * @param logtime
     * @param offerdate
     * @param destlon
     * @param vehicle
     * @param regno
     * @param carmodel
     * @param startlat
     * @param contact
     * @param bikemodel
     * @param logdate
     * @param offertime
     * @param name
     * @param features
     * @param startlon
     * @param seats
     */
    public FindRydeCode(String name, String contact, String startlat, String startlon, String destlat, String destlon, String offerdate, String offertime, String bikemodel, String logdate, String logtime, String regno, String seats, String vehicle, String features, String carmodel) {
        super();
        this.name = name;
        this.contact = contact;
        this.startlat = startlat;
        this.startlon = startlon;
        this.destlat = destlat;
        this.destlon = destlon;
        this.offerdate = offerdate;
        this.offertime = offertime;
        this.bikemodel = bikemodel;
        this.logdate = logdate;
        this.logtime = logtime;
        this.regno = regno;
        this.seats = seats;
        this.vehicle = vehicle;
        this.features = features;
        this.carmodel = carmodel;
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

    public String getStartlat() {
        return startlat;
    }

    public void setStartlat(String startlat) {
        this.startlat = startlat;
    }

    public String getStartlon() {
        return startlon;
    }

    public void setStartlon(String startlon) {
        this.startlon = startlon;
    }

    public String getDestlat() {
        return destlat;
    }

    public void setDestlat(String destlat) {
        this.destlat = destlat;
    }

    public String getDestlon() {
        return destlon;
    }

    public void setDestlon(String destlon) {
        this.destlon = destlon;
    }

    public String getOfferdate() {
        return offerdate;
    }

    public void setOfferdate(String offerdate) {
        this.offerdate = offerdate;
    }

    public String getOffertime() {
        return offertime;
    }

    public void setOffertime(String offertime) {
        this.offertime = offertime;
    }

    public String getBikemodel() {
        return bikemodel;
    }

    public void setBikemodel(String bikemodel) {
        this.bikemodel = bikemodel;
    }

    public String getLogdate() {
        return logdate;
    }

    public void setLogdate(String logdate) {
        this.logdate = logdate;
    }

    public String getLogtime() {
        return logtime;
    }

    public void setLogtime(String logtime) {
        this.logtime = logtime;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

}
