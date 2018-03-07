
package com.example.angeo.newproject.Retrofit.RegisterUser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterUserModule {

    @SerializedName("code")
    @Expose
    private RegisterCode code;
    @SerializedName("status")
    @Expose
    private RegisterStatus status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RegisterUserModule() {
    }

    /**
     * 
     * @param status
     * @param code
     */
    public RegisterUserModule(RegisterCode code, RegisterStatus status) {
        super();
        this.code = code;
        this.status = status;
    }

    public RegisterCode getCode() {
        return code;
    }

    public void setCode(RegisterCode code) {
        this.code = code;
    }

    public RegisterStatus getStatus() {
        return status;
    }

    public void setStatus(RegisterStatus status) {
        this.status = status;
    }

}
