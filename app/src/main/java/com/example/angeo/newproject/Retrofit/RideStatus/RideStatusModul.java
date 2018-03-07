
package com.example.angeo.newproject.Retrofit.RideStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RideStatusModul {

    @SerializedName("status")
    @Expose
    private RideStatus status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RideStatusModul() {
    }

    /**
     * 
     * @param status
     */
    public RideStatusModul(RideStatus status) {
        super();
        this.status = status;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

}
