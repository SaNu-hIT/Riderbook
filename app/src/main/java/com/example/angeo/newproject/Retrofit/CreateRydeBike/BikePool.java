
package com.example.angeo.newproject.Retrofit.CreateRydeBike;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BikePool {

    @SerializedName("status")
    @Expose
    private BikePoolStatus status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BikePool() {
    }

    /**
     * 
     * @param status
     */
    public BikePool(BikePoolStatus status) {
        super();
        this.status = status;
    }

    public BikePoolStatus getStatus() {
        return status;
    }

    public void setStatus(BikePoolStatus status) {
        this.status = status;
    }

}
