package com.example.rentalagency;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RentPropertyAdapter extends RecyclerView.Adapter<RentPropertyViewHolder>{

    private Context context;
    private ArrayList<Property> properties;
    private PropertyClicked propertyClicked;

    public RentPropertyAdapter(Context context, ArrayList<Property> properties, PropertyClicked propertyClicked) {
        this.context = context;
        this.properties = properties;
        this.propertyClicked = propertyClicked;
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
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                propertyClicked.onClick(properties.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }
}
