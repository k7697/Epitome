package com.epitome.api;
import android.util.Base64;

import com.epitome.model.Category;
import com.epitome.model.Product;
import com.epitome.response.CartJasonResponse;
import com.epitome.response.CategoryResponse;
import com.epitome.response.OrderHistoryJsonResponse;
import com.epitome.response.ProductJsonResponse;
import com.epitome.response.ProductResponse;
import com.epitome.response.SubCategoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    String unm = "admin";
    String pwd = "1234";
    String base = unm + ":" + pwd;
    final String keyHeader = "epitome@123";
    final String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);

    @GET("category/")
    Call<CategoryResponse> getCategories(
            @Header("Authorization") String credential,
            @Header("X-API-KEY") String key
    );

    @GET("subcategory/")
    Call<List<Category>> getSubCategories(
            @Header("Authorization") String credential,
            @Header("X-API-KEY") String key
    );//where you called this?

    @GET("subcategory/{id}")
    Call<SubCategoryResponse> getSubCategoriesById(
            @Header("Authorization") String credential,
            @Header("X-API-KEY") String key,
            @Path("id") String id
    );

    //order history..
    @GET("Order/userorder/{id}")
    Call<OrderHistoryJsonResponse> getOrderHistory(

            @Header("Authorization") String credential,
            @Header("X-API-KEY") String key,
            @Query("id") String userId
    );


    //cart..
    @GET("cart/{userid}")
    Call<CartJasonResponse> getCart(
            @Header("Authorization") String credential,
            @Header("X-API-KEY") String key,
            @Path("userid") String userId
    );

    //cart..
    @POST("cart/")
    Call<CartJasonResponse> addToCart(
            @Header("Authorization") String credential,
            @Header("X-API-KEY") String key,
            @Field("cart_id") String cartId

    );

    @GET("product/")
    Call<ProductJsonResponse> getAllProducts(
            @Header("Authorization") String credential,
            @Header("X-API-KEY") String key
    );

    @GET("product/{id}")
    Call<ProductResponse> getProductById(
            @Header("Authorization") String credential,
            @Header("X-API-KEY") String key,
            @Path("id") String id
    );

    @GET("getproductbycategoryid/{id}")
    Call<List<Product>> getProductByCategoryId(
            @Header("Authorization") String credential,
            @Header("X-API-KEY") String key,
            @Path("id") String id
    );


}
