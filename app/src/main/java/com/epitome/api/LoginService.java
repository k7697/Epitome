package com.epitome.api;

import com.epitome.response.JsonResponse;
import com.epitome.response.SignupJsonResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginService {
    @FormUrlEncoded
    @POST("login/")
     Call<JsonResponse> basicLogin(
            @Header("X-API-KEY") String keyHeader,
            @Header("authorization") String authHeader,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("signup/")
    Call<SignupJsonResponse> basicSignup(
            @Header("X-API-KEY") String keyHeader,
            @Header("authorization") String authHeader,
            @Field("fname") String firstname,
            @Field("lname") String lastname,
            @Field("contact_no") String contact,
            @Field("email") String email,
            @Field("password") String password,
            @Field("gender") int gender,
               @Field("dob") String dob
    );

}
