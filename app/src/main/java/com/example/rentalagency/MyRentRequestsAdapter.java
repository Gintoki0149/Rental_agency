package com.example.rentalagency;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRentRequestsAdapter extends RecyclerView.Adapter<MyRentRequestsViewHolder> {
    private Context context;
    private ArrayList<Request> requests;
    private RemoveRequest removeRequest;
    public MyRentRequestsAdapter(Context context,ArrayList<Request> requests,RemoveRequest removeRequest){
        this.context = context;
        this.requests = requests;
        this.removeRequest = removeRequest;
    }
    @NonNull
    @Override
    public MyRentRequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyRentRequestsViewHolder(LayoutInflater.from(context).inflate(R.layout.my_rent_request_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyRentRequestsViewHolder holder, int position) {
        Request theRequest = requests.get(position);
        holder.tovalue.setText(theRequest.getTo());
        holder.propertyvalue.setText(theRequest.getMessage());
        holder.requeststatus.setText(theRequest.getStatus());
        switch(holder.requeststatus.getText().toString()){
            case "Accepted":
                holder.cardView.setCardBackgroundColor(Color.GREEN);
                break;
            case "Rejected":
                holder.cardView.setCardBackgroundColor(Color.RED);
                break;
            default:
                holder.cardView.setCardBackgroundColor(Color.rgb(255,255,204));
        }
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeRequest.remove(theRequest);
            }
        });
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }
}
