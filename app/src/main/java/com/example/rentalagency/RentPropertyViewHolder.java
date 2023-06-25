package com.example.rentalagency;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RentPropertyViewHolder extends RecyclerView.ViewHolder{
    TextView Address,Rent,City,Floor;
    TextView AddressValue,RentValue,CityValue,FloorValue;
    public RentPropertyViewHolder(@NonNull View itemView) {
        super(itemView);
        Address = itemView.findViewById(R.id.addresspropertyitem);
        AddressValue = itemView.findViewById(R.id.addresspropertyitemvalue);
        Rent = itemView.findViewById(R.id.rentpropertyitem);
        RentValue = itemView.findViewById(R.id.rentpropertyitemvalue);
        City = itemView.findViewById(R.id.citypropertyitem);
        CityValue = itemView.findViewById(R.id.citypropertyitemvalue);
        Floor = itemView.findViewById(R.id.floorspropertyitem);
        FloorValue = itemView.findViewById(R.id.floorspropertyitemvalue);
    }
}
