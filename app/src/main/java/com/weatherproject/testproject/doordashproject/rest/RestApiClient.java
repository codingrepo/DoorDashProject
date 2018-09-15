package com.weatherproject.testproject.doordashproject.rest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {

    public static final String RESTAURANT_API_ENDPOINT = "https://api.doordash.com";
    private  static Retrofit retrofit = null;

    public static Retrofit getClient(){

        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(RESTAURANT_API_ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
