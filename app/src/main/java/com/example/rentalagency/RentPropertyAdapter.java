package com.example.rentalagency;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RentPropertyAdapter extends RecyclerView.Adapter<RentPropertyViewHolder>{

    Context context;
    ArrayList<Property> properties;

    public RentPropertyAdapter(Context context, ArrayList<Property> properties) {
        this.context = context;
        this.properties = properties;
    }

    @NonNull
    @Override
    public RentPropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RentPropertyViewHolder(LayoutInflater.from(context).inflate(R.layout.property_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RentPropertyViewHolder holder, int position) {
        String rentval = String.valueOf(properties.get(position).getRent());
        String floorval = String.valueOf(properties.get(position).getFloors());
        holder.AddressValue.setText(properties.get(position).getAddress());
        holder.CityValue.setText(properties.get(position).getCity());
        holder.RentValue.setText(rentval);
        holder.FloorValue.setText(floorval);
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }
}
