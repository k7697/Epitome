package com.epitome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Cart {

    @SerializedName("cart_id")
    @Expose
    private String cartId;

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("cart_info")
    @Expose
    private ArrayList<CartProducts> cartProducts;


    public Cart(String cartId, String userId, ArrayList<CartProducts> cartProducts) {
        this.cartId = cartId;
        this.userId = userId;
        this.cartProducts = cartProducts;
    }
    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<CartProducts> getCartProducts() {

        return cartProducts;
    }

    public void setCartProducts(ArrayList<CartProducts> cartProducts) {
        this.cartProducts = cartProducts;
    }


}
