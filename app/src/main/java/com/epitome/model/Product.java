package com.epitome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {
    @SerializedName("product_id")
    @Expose
    public String productID;
    @SerializedName("product_name")
    @Expose
    private String productName;

    @SerializedName("category_id")
    @Expose
    private String categoryId;

    @SerializedName("decription")
    @Expose
    private String description;

    @SerializedName("product_info_id")
    @Expose
    private String productspecsid;

    @SerializedName("reorder_level")
    @Expose
    private String reorder_level;

    @SerializedName("date_modified")
    @Expose
    private String date_modified;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("stock")
    @Expose
    private String stock;

    @SerializedName("discount_percentage")
    @Expose
    private String discountPercentage;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("date_created")
    @Expose
    private String dateCreated;

    @SerializedName("images")
    @Expose
    private List<ProductImages> images;

    @SerializedName("specs")
    @Expose
    private ProductSpecs specs;

    @SerializedName("created_by")
    @Expose
    private String created_by;

    @SerializedName("modified_by")
    @Expose
    private String modified_by;

    public Product(String productID, String productName, String categoryId, String description, String productspecsid, String reorder_level, String date_modified, String price, String stock, String discountPercentage, String status, String dateCreated, List<ProductImages> images, ProductSpecs specs, String created_by, String modified_by) {
        this.productID = productID;
        this.productName = productName;
        this.categoryId = categoryId;
        this.description = description;
        this.productspecsid = productspecsid;
        this.reorder_level = reorder_level;
        this.date_modified = date_modified;
        this.price = price;
        this.stock = stock;
        this.discountPercentage = discountPercentage;
        this.status = status;
        this.dateCreated = dateCreated;
        this.images = images;
        this.specs = specs;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductspecsid(String productspecsid) {
        this.productspecsid = productspecsid;
    }

    public void setReorder_level(String reorder_level) {
        this.reorder_level = reorder_level;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setImages(List<ProductImages> images) {
        this.images = images;
    }

    public void setSpecs(ProductSpecs specs) {
        this.specs = specs;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    public String getProductspecsid() {
        return productspecsid;
    }

    public String getReorder_level() {
        return reorder_level;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public String getPrice() {
        return price;
    }

    public String getStock() {
        return stock;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public String getStatus() {
        return status;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public List<ProductImages> getImages() {
        return images;
    }

    public ProductSpecs getSpecs() {
        return specs;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getModified_by() {
        return modified_by;
    }
}


