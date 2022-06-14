
package com.example.phonetracker.models;

import java.util.List;
import javax.annotation.Generated;

import com.example.phonetracker.models.Phone;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Data {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("phones")
    @Expose
    private List<Phone> phones = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param phones
     * @param title
     */
    public Data(String title, List<Phone> phones) {
        super();
        this.title = title;
        this.phones = phones;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

}
