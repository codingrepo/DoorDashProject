package com.weatherproject.testproject.doordashproject.rest;

import com.weatherproject.testproject.doordashproject.model.JsonDataRestaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpointInterface {

    @GET("/v2/restaurant")
    Call<List<JsonDataRestaurant>> getRestaurantData(@Query("lat") String lat, @Query("lng") String lng);

}
