package com.epitome.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.epitome.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryJsonResponse {
    @SerializedName("order")
    @Expose
    public List<Order> orders;
    public OrderHistoryJsonResponse(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
