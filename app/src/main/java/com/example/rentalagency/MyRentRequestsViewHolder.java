package com.example.rentalagency;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyRentRequestsViewHolder extends RecyclerView.ViewHolder {
    TextView TO,tovalue,prop,propertyvalue,requeststatus;
    Button del;
    CardView cardView;
    public MyRentRequestsViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.myrequestsviewholder);
        TO = itemView.findViewById(R.id.to);
        tovalue = itemView.findViewById(R.id.to_value);
        prop = itemView.findViewById(R.id.message);
        propertyvalue = itemView.findViewById(R.id.property_value);
        del = itemView.findViewById(R.id.delete);
        requeststatus = itemView.findViewById(R.id.request_status_value);
    }
}
