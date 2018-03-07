
package com.example.angeo.newproject.Retrofit.HomePage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeCode {

    @SerializedName("contentid")
    @Expose
    private String contentid;
    @SerializedName("contentname")
    @Expose
    private String contentname;
    @SerializedName("imageurl")
    @Expose
    private String imageurl;
    @SerializedName("visiblestatus")
    @Expose
    private String visiblestatus;

    /**
     * No args constructor for use in serialization
     * 
     */
    public HomeCode() {
    }

    /**
     * 
     * @param contentid
     * @param visiblestatus
     * @param imageurl
     * @param contentname
     */
    public HomeCode(String contentid, String contentname, String imageurl, String visiblestatus) {
        super();
        this.contentid = contentid;
        this.contentname = contentname;
        this.imageurl = imageurl;
        this.visiblestatus = visiblestatus;
    }

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }

    public String getContentname() {
        return contentname;
    }

    public void setContentname(String contentname) {
        this.contentname = contentname;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getVisiblestatus() {
        return visiblestatus;
    }

    public void setVisiblestatus(String visiblestatus) {
        this.visiblestatus = visiblestatus;
    }

}
