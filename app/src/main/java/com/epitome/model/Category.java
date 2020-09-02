package com.epitome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Category {



    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    private boolean expanded;

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
//                ", parentCategory=" + parentCategory +

                '}';
    }
/*
"category_id": "5",
        "category_name": "shoes",
        "description": "",
        "parent_category": null,
        "status": "active",
        "date_created": "2020-03-24 09:40:12",
        "modified_date": "2020-03-24 04:40:12",
        "created_by": "2",
        "modified_by": "2"
 */
    @SerializedName("category_id")
    @Expose
    private String categoryId;

    @SerializedName("category_name")
    @Expose
    private String categoryName;

    @SerializedName("description")
    @Expose
    private String categoryDescription;

    @SerializedName("parent_category")
    @Expose
   private String parentCategory;

 /*   @SerializedName("status")
    @Expose
    private Boolean isActive;*/

    public Category( String categoryId, String categoryName, String categoryDescription, String parentCategory) {
        this.expanded = false;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.parentCategory = parentCategory;

    }

    //setters and getters


    public String getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

//    public Category getParentCategory() {
//        return parentCategory;
//    }
//
//    public void setParentCategory(Category parentCategory) {
//        this.parentCategory = parentCategory;
//    }


}
