package com.epitome.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductSpecs {
    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("size")
    @Expose
    private String sizes;
    @SerializedName("composition")
    @Expose
    private String composition;

    public ProductSpecs(String color, String sizes, String composition) {
        this.color = color;
        this.sizes = sizes;
        this.composition = composition;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }
}