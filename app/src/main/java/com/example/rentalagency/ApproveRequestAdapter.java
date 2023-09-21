package com.example.rentalagency;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ApproveRequestAdapter extends RecyclerView.Adapter<ApproveRequestViewHolder> {
    ArrayList<Request> requests;
    Context context;
    ApproveRequestInterface approveRequestInterface;
    public ApproveRequestAdapter(Context context,ArrayList<Request> requests,ApproveRequestInterface approveRequestInterface){
        this.context = context;
        this.requests = requests;
        this.approveRequestInterface = approveRequestInterface;
    }
    @NonNull
    @Override
    public ApproveRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ApproveRequestViewHolder(LayoutInflater.from(context).inflate(R.layout.approve_request_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ApproveRequestViewHolder holder, int position) {
        Request theRequest = requests.get(position);
        holder.from_value.setText(theRequest.getFrom());
        holder.msg.setText(theRequest.getMessage());
        holder.statusvalue.setText(theRequest.getStatus());
        switch(holder.statusvalue.getText().toString()){
            case "Removed":
                holder.approvecard.setCardBackgroundColor(Color.GRAY);
                break;
            default:
                holder.approvecard.setCardBackgroundColor(Color.rgb(255,255,204));
        }
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approveRequestInterface.onReject(theRequest);
            }
        });
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approveRequestInterface.onAccept(theRequest);
            }
        });
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }
}
