package com.epitome.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.epitome.model.Category;

import java.util.List;

public class SubCategoryResponse {
    @SerializedName("subcategories")
    @Expose
    public List<Category> subcategories;

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;

    public List<Category> getSubcategories() {
        return subcategories;
    }
    public void setSubcategories(List<Category> subcategories) {
        this.subcategories = subcategories;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
