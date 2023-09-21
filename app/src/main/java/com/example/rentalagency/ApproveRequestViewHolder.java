package com.example.rentalagency;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ApproveRequestViewHolder extends RecyclerView.ViewHolder {
    RelativeLayout layout;
    TextView from,from_value,msg,statusvalue;
    ImageView accept,reject;
    CardView approvecard;
    public ApproveRequestViewHolder(@NonNull View itemView) {
        super(itemView);
        approvecard = itemView.findViewById(R.id.approve_card);
        layout = itemView.findViewById(R.id.rellayout);
        from = itemView.findViewById(R.id.approve_request_from);
        from_value = itemView.findViewById(R.id.approve_request_from_value);
        msg = itemView.findViewById(R.id.approve_request_message);
        statusvalue = itemView.findViewById(R.id.status_value);
        accept = itemView.findViewById(R.id.approve);
        reject = itemView.findViewById(R.id.reject);
    }
}
