package com.epitome.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.epitome.model.User;

public class JsonResponse {
    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("login_status")
    @Expose
    public String   login_status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("user")
    @Expose
    public User user;


    public JsonResponse(int status, String login_status, String message, User user) {
        this.status = status;
        this.login_status = login_status;
        this.message = message;
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLogin_status() {
        return login_status;
    }

    public void setLogin_status(String login_status) {
        this.login_status = login_status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
