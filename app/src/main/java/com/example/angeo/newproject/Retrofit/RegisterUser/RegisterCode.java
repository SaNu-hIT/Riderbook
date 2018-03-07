
package com.example.angeo.newproject.Retrofit.RegisterUser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterCode {

    @SerializedName("fail")
    @Expose
    private String fail;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RegisterCode() {
    }

    /**
     * 
     * @param fail
     */
    public RegisterCode(String fail) {
        super();
        this.fail = fail;
    }

    public String getFail() {
        return fail;
    }

    public void setFail(String fail) {
        this.fail = fail;
    }

}
