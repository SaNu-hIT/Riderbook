
package com.example.angeo.newproject.Retrofit.FindRyde;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FindRydeModule {

    @SerializedName("code")
    @Expose
    private List<FindRydeCode> code = null;
    @SerializedName("status")
    @Expose
    private FindRydeStatus status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FindRydeModule() {
    }

    /**
     * 
     * @param status
     * @param code
     */
    public FindRydeModule(List<FindRydeCode> code, FindRydeStatus status) {
        super();
        this.code = code;
        this.status = status;
    }

    public List<FindRydeCode> getCode() {
        return code;
    }

    public void setCode(List<FindRydeCode> code) {
        this.code = code;
    }

    public FindRydeStatus getStatus() {
        return status;
    }

    public void setStatus(FindRydeStatus status) {
        this.status = status;
    }

}
