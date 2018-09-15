package com.weatherproject.testproject.doordashproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weatherproject.testproject.doordashproject.model.JsonDataRestaurant;
import com.weatherproject.testproject.doordashproject.rest.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMainList;
    private CustomRestaurantListAdapter customRestaurantListAdapter;
    private List<JsonDataRestaurant> jsonDataRestaurantList;
    private Gson gson;
    public static final String GSON_OBJ ="gson_obj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMainList = findViewById(R.id.rvMainList);
        rvMainList.setHasFixedSize(true);
        jsonDataRestaurantList = new ArrayList<>();

        if(savedInstanceState!=null && savedInstanceState.containsKey(GSON_OBJ)){
            Type type = new TypeToken<ArrayList<JsonDataRestaurant>>(){}.getType();
            String jsonString = savedInstanceState.getString(GSON_OBJ, "");
            jsonDataRestaurantList = (new Gson()).fromJson(jsonString, type);
            populateRecycleView(jsonDataRestaurantList);
        }else {
            loadData();
        }
    }

    public void loadData(){
        ApiEndpointInterface apiService =
                RestApiClient.getClient().create(ApiEndpointInterface.class);
        retrofit2.Call<List<JsonDataRestaurant>> call = apiService.getRestaurantData("37.422740","-122.139956");
        call.enqueue(new Callback<List<JsonDataRestaurant>>() {
            @Override
            public void onResponse(Call<List<JsonDataRestaurant>> call, Response<List<JsonDataRestaurant>> response) {
                jsonDataRestaurantList=response.body();
                if(!jsonDataRestaurantList.isEmpty()){
                    populateRecycleView(jsonDataRestaurantList);
                }else {
                    Toast.makeText(getApplicationContext(), "No data loaded", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<JsonDataRestaurant>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"No data loaded",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void populateRecycleView(List<JsonDataRestaurant> jsonDataRestaurantList){
        customRestaurantListAdapter = new CustomRestaurantListAdapter(this,jsonDataRestaurantList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rvMainList.setLayoutManager(layoutManager);
        rvMainList.setAdapter(customRestaurantListAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(jsonDataRestaurantList!=null) {
            gson = new Gson();
            String json = gson.toJson(jsonDataRestaurantList);
            outState.putString(GSON_OBJ, json);
            super.onSaveInstanceState(outState);
        }
    }
}
