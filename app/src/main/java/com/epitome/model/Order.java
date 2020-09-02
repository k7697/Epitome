package com.epitome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;


public class Order {
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("promotion_id")
    @Expose
    private String promoId;

    @SerializedName("shipping_address_id")
    @Expose
    private String shipAddressId;

    @SerializedName("mail_address_id")
    @Expose
    private String mailAddressId;


    @SerializedName("date")
    @Expose
    private Date date;


    @SerializedName("shipped_status")
    @Expose
    private String shippedStatus;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;


    @SerializedName("total")
    @Expose
    private String total;


    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("shipping_address")
    @Expose
    private Address shipAddress;


    @SerializedName("mail_address")
    @Expose
    private Address mailAddress;

    @SerializedName("products")
    @Expose
    private List<CartProducts> products;


    public Order(String orderId, String userId, String promoId, String shipAddressId, String mailAddressId, Date date, String shippedStatus, String orderStatus, String total, User user, Address shipAddress, Address mailAddress, List<CartProducts> products) {
        this.orderId = orderId;
        this.userId = userId;
        this.promoId = promoId;
        this.shipAddressId = shipAddressId;
        this.mailAddressId = mailAddressId;
        this.date = date;
        this.shippedStatus = shippedStatus;
        this.orderStatus = orderStatus;
        this.total = total;
        this.user = user;
        this.shipAddress = shipAddress;
        this.mailAddress = mailAddress;
        this.products = products;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public String getShipAddressId() {
        return shipAddressId;
    }

    public void setShipAddressId(String shipAddressId) {
        this.shipAddressId = shipAddressId;
    }

    public String getMailAddressId() {
        return mailAddressId;
    }

    public void setMailAddressId(String mailAddressId) {
        this.mailAddressId = mailAddressId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getShippedStatus() {
        return shippedStatus;
    }

    public void setShippedStatus(String shippedStatus) {
        this.shippedStatus = shippedStatus;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(Address shipAddress) {
        this.shipAddress = shipAddress;
    }

    public Address getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(Address mailAddress) {
        this.mailAddress = mailAddress;
    }

    public List<CartProducts> getProducts() {
        return products;
    }

    public void setProducts(List<CartProducts> products) {
        this.products = products;
    }
}
