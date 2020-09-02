package com.epitome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class OrderInfo {

    @SerializedName("orderId")
    @Expose
    private int orderId;

    @SerializedName("productId")
    @Expose
    private int productId;

    @SerializedName("qty")
    @Expose
    private int qty;

    @SerializedName("satus")
    @Expose
    private String status;

    @SerializedName("price")
    @Expose
    private int price;

    //setters and getters

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}