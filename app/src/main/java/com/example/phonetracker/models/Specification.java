
package com.example.phonetracker.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Specification {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("specs")
    @Expose
    private List<Spec> specs = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Specification() {
    }

    /**
     * 
     * @param specs
     * @param title
     */
    public Specification(String title, List<Spec> specs) {
        super();
        this.title = title;
        this.specs = specs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Spec> getSpecs() {
        return specs;
    }

    public void setSpecs(List<Spec> specs) {
        this.specs = specs;
    }

}
