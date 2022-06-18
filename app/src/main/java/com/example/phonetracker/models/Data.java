
package com.example.phonetracker.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Data {

    private  String id;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("phone_name")
    @Expose
    private String phoneName;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("phone_images")
    @Expose
    private List<String> phoneImages = null;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("dimension")
    @Expose
    private String dimension;
    @SerializedName("os")
    @Expose
    private String os;
    @SerializedName("storage")
    @Expose
    private String storage;
    @SerializedName("specifications")
    @Expose
    private List<Specification> specifications = null;
    private String pushId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param phoneImages
     * @param thumbnail
     * @param os
     * @param releaseDate
     * @param storage
     * @param brand
     * @param dimension
     * @param specifications
     * @param phoneName
     */
    public Data(String brand, String phoneName, String thumbnail, List<String> phoneImages, String releaseDate, String dimension, String os, String storage, List<Specification> specifications,  String id) {
        super();
        this.brand = brand;
        this.id = id;
        this.phoneName = phoneName;
        this.thumbnail = thumbnail;
        this.phoneImages = phoneImages;
        this.releaseDate = releaseDate;
        this.dimension = dimension;
        this.os = os;
        this.storage = storage;
        this.specifications = specifications;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<String> getPhoneImages() {
        return phoneImages;
    }

    public void setPhoneImages(List<String> phoneImages) {
        this.phoneImages = phoneImages;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public List<Specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Specification> specifications) {
        this.specifications = specifications;
    }
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

}
