package com.example.rentalagency;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyRentRequestsViewHolder extends RecyclerView.ViewHolder {
    TextView TO,tovalue,prop,propertyvalue;
    Button del;
    ConstraintLayout constraintLayout;
    public MyRentRequestsViewHolder(@NonNull View itemView) {
        super(itemView);
        constraintLayout = itemView.findViewById(R.id.myrequestsviewholder);
        TO = itemView.findViewById(R.id.to);
        tovalue = itemView.findViewById(R.id.to_value);
        prop = itemView.findViewById(R.id.message);
        propertyvalue = itemView.findViewById(R.id.property_value);
        del = itemView.findViewById(R.id.delete);
    }
}
