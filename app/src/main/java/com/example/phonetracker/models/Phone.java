
package com.example.phonetracker.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Phone {

    @SerializedName("phone_name")
    @Expose
    private String phoneName;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("detail")
    @Expose
    private String detail;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Phone() {
    }

    /**
     * 
     * @param image
     * @param detail
     * @param phoneName
     * @param slug
     */
    public Phone(String phoneName, String slug, String image, String detail) {
        super();
        this.phoneName = phoneName;
        this.slug = slug;
        this.image = image;
        this.detail = detail;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
