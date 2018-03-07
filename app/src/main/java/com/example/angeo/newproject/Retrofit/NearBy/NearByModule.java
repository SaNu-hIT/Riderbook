
package com.example.angeo.newproject.Retrofit.NearBy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NearByModule {

    @SerializedName("code")
    @Expose
    private List<NearByCode> code = null;
    @SerializedName("status")
    @Expose
    private NearByStatus status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public NearByModule() {
    }

    /**
     * 
     * @param status
     * @param code
     */
    public NearByModule(List<NearByCode> code, NearByStatus status) {
        super();
        this.code = code;
        this.status = status;
    }

    public List<NearByCode> getCode() {
        return code;
    }

    public void setCode(List<NearByCode> code) {
        this.code = code;
    }

    public NearByStatus getStatus() {
        return status;
    }

    public void setStatus(NearByStatus status) {
        this.status = status;
    }

}
