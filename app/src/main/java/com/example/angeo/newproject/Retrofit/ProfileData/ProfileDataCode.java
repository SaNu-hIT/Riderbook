
package com.example.angeo.newproject.Retrofit.ProfileData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileDataCode {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("imageurl")
    @Expose
    private String imageurl;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProfileDataCode() {
    }

    /**
     * 
     * @param email
     * @param name
     * @param gender
     * @param lastname
     * @param imageurl
     */
    public ProfileDataCode(String name, String lastname, String gender, String email, String imageurl) {
        super();
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.email = email;
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

}
