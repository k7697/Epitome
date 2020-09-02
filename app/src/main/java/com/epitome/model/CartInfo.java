package com.epitome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartInfo {

    @SerializedName("product_id")
    @Expose
    private int productId;

    @SerializedName("quantity")
    @Expose
    private int quantity;

}
