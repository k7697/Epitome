package com.epitome.response;

import com.epitome.model.Cart;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartJasonResponse {
    @SerializedName("status")
    @Expose
    public int status;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("cart")
    @Expose
    public Cart cart;

    public CartJasonResponse(int status, String message, Cart cart) {
        this.status = status;
        this.message = message;
        this.cart = cart;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
