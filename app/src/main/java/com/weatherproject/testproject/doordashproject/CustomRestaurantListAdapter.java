package com.weatherproject.testproject.doordashproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.weatherproject.testproject.doordashproject.model.JsonDataRestaurant;
import java.util.List;


public class CustomRestaurantListAdapter extends RecyclerView.Adapter<CustomRestaurantListAdapter.RestaurantListView> {
    private List<JsonDataRestaurant> RestaurantLists ;
    private Context context;

    public CustomRestaurantListAdapter(@NonNull Context context, List<JsonDataRestaurant> RestaurantLists) {
        this.RestaurantLists = RestaurantLists;
        this.context = context;
    }


    @NonNull
    @Override
    public RestaurantListView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new RestaurantListView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantListView holder, int position) {
        JsonDataRestaurant RestaurantList = RestaurantLists.get(position);

        Picasso.get().load(RestaurantList.getCover_img_url()).into(holder.imgRestaurant);
        holder.tvRestaurantName.setText(RestaurantList.getBusiness().getName());

        int tagsCount = RestaurantList.getTags().size();
        String strDescription="";
        if(tagsCount==1){
             strDescription = RestaurantList.getTags().get(0);
        }else if(tagsCount>1){
             strDescription = RestaurantList.getTags().get(0)+", "+RestaurantList.getTags().get(1);
        }
        holder.tvDescription.setText(strDescription);

         String status = RestaurantList.getStatus();
            if(status.equals("Pre-order for Pre-order")){
                holder.tvAsapTime.setText("Pre-order");
            }else{
                holder.tvAsapTime.setText(status);
            }


    }

    @Override
    public int getItemCount() {
        return RestaurantLists.size();
    }

    class RestaurantListView extends RecyclerView.ViewHolder{
        ImageView imgRestaurant;
        TextView tvRestaurantName;
        TextView tvDescription;
        TextView tvAsapTime;
        public RestaurantListView(View itemView) {
            super(itemView);
            imgRestaurant = itemView.findViewById(R.id.imgRestaurant);
            tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvAsapTime = itemView.findViewById(R.id.tvAsapTime);
        }
    }
}
