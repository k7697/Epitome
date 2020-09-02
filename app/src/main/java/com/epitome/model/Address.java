package com.epitome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Address {

    @SerializedName("address_id")
    @Expose
    private String addressId;

    @SerializedName("street")
    @Expose
    private String street;

    @SerializedName("civil_No")
    @Expose
    private String civicNo;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("postal_code")
    @Expose
    private String postalCode;

    @SerializedName("apartment")
    @Expose
    private int apartmentNumber;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCivicNo() {
        return civicNo;
    }

    public void setCivicNo(String civicNo) {
        this.civicNo = civicNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public Address(String addressId, String street, String civicNo, String city, String state, String country, String postalCode, int apartmentNumber) {
        this.addressId = addressId;
        this.street = street;
        this.civicNo = civicNo;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.apartmentNumber = apartmentNumber;
    }
}
