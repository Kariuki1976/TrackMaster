
package com.example.phonetracker.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Spec {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("val")
    @Expose
    private List<String> val = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Spec() {
    }

    /**
     * 
     * @param val
     * @param key
     */
    public Spec(String key, List<String> val) {
        super();
        this.key = key;
        this.val = val;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getVal() {
        return val;
    }

    public void setVal(List<String> val) {
        this.val = val;
    }

}
