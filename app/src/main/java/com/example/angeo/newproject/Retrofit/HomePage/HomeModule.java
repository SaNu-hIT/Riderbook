
package com.example.angeo.newproject.Retrofit.HomePage;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeModule {

    @SerializedName("code")
    @Expose
    private List<HomeCode> code = null;
    @SerializedName("status")
    @Expose
    private HomeStatus status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public HomeModule() {
    }

    /**
     * 
     * @param status
     * @param code
     */
    public HomeModule(List<HomeCode> code, HomeStatus status) {
        super();
        this.code = code;
        this.status = status;
    }

    public List<HomeCode> getCode() {
        return code;
    }

    public void setCode(List<HomeCode> code) {
        this.code = code;
    }

    public HomeStatus getStatus() {
        return status;
    }

    public void setStatus(HomeStatus status) {
        this.status = status;
    }

}
