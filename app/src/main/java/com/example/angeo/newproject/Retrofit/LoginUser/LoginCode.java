
package com.example.angeo.newproject.Retrofit.LoginUser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginCode {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("contact")
    @Expose
    private String contact;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LoginCode() {
    }

    /**
     * 
     * @param userid
     * @param contact
     * @param success
     */
    public LoginCode(String success, String userid, String contact) {
        super();
        this.success = success;
        this.userid = userid;
        this.contact = contact;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
