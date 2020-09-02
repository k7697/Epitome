package com.epitome.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.epitome.model.Product;

import java.util.List;

/*

{
    "product": [
        {
            "product_id": "1",
            "product_name": "asdfghjkl",
            "decription": "asdfghbjn",
            "price": "99.99",
            "stock": "100",
            "reorder_level": "10",
            "discount_percentage": "2.00",
            "category_id": "8",
            "product_info_id": "1",
            "status": "active",
            "date_created": "2020-03-31 20:42:30",
            "date_modified": "2020-04-04 18:49:45",
            "created_by": "2",
            "modified_by": "2",
            "images": [
                {
                    "url": "http://stylestamp.dipenoverseas.com/images/fulllogo.png",
                    "alt_text": "product image"
                },
                {
                    "url": "http://stylestamp.dipenoverseas.com/images/logo.png",
                    "alt_text": "product image"
                }
            ],
            "specs": {
                "color": "black,green,yellow",
                "size": "S,XS,M,L,XL,XXL",
                "composition": "cotton"
            }
        }
    ]
}
 */
/*
{
    "status": 1,
    "message": "No product were found.",
    "product": {
        "product_id": "1"
    }
}
 */
public class ProductJsonResponse {
    @SerializedName("status")
    @Expose
    public int status;

    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("product")
    @Expose
    public List<Product> products;

    public ProductJsonResponse(int status, String message, List<Product> products) {
        this.status = status;
        this.message = message;
        this.products = products;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

