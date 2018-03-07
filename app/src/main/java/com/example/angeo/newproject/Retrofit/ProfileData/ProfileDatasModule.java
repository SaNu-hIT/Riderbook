
package com.example.angeo.newproject.Retrofit.ProfileData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileDatasModule {

    @SerializedName("code")
    @Expose
    private ProfileDataCode code;
    @SerializedName("status")
    @Expose
    private ProfileDataStatus status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProfileDatasModule() {
    }

    /**
     * 
     * @param status
     * @param code
     */
    public ProfileDatasModule(ProfileDataCode code, ProfileDataStatus status) {
        super();
        this.code = code;
        this.status = status;
    }

    public ProfileDataCode getCode() {
        return code;
    }

    public void setCode(ProfileDataCode code) {
        this.code = code;
    }

    public ProfileDataStatus getStatus() {
        return status;
    }

    public void setStatus(ProfileDataStatus status) {
        this.status = status;
    }

}
