package com.example.rentalagency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ApproveRentRequest extends AppCompatActivity implements ApproveRequestInterface{
    RecyclerView recyclerView;
    DatabaseReference db;
    ArrayList<Request> requests;
    ApproveRequestAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_rent_request);
        requests = new ArrayList<>();
        recyclerView = findViewById(R.id.approve_request_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ApproveRequestAdapter(getApplicationContext(),requests,this);
        recyclerView.setAdapter(adapter);
        db = FirebaseDatabase.getInstance("https://rental-agency-11805-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        db.child("rent_requests").child("to").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                requests.clear();
                for(DataSnapshot snap:snapshot.getChildren()){  //receivers
                    if(snap.getKey().compareTo(LandingPage.curuser) == 0){
                        for(DataSnapshot ds:snap.getChildren()){    //senders
                            String sender = ds.getKey();
                            for(DataSnapshot s:ds.getChildren()){   //address
                                requests.add(new Request(sender,LandingPage.curuser,s.child("request").getValue().toString(),s.getKey(),s.child("status").getValue().toString()));
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onAccept(Request request) {
        String receiver = request.getTo();
        String address = request.getAddress();
        String sender = request.getFrom();
        db.child("rent_requests").child("to").child(receiver).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren()){  //senders
                    String user = data.getKey();
                    if(user.compareTo(sender) == 0){
                        Log.d("accept","working");
                        db.child("rent_requests").child("from").child(sender).child(receiver).child(address).child("status").setValue("Accepted");
                    }
                    else{
                        db.child("rent_requests").child("from").child(user).child(receiver).child(address).child("status").setValue("Rejected");
                    }
                }
                db.child("rent_requests").child("to").child(receiver).child(sender).child(address).child("status").setValue("Waiting For Payment");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onReject(Request request) {
        int len = request.getFrom().length()+(" has requested to rent the property at Address : ").length();
        db.child("rent_requests").child("to").child(request.getTo()).child(request.getFrom()).child(request.getAddress()).removeValue();
        if(request.getStatus().compareTo("Pending") == 0) {
            db.child("rent_requests").child("from").child(request.getFrom()).child(request.getTo()).child(request.getAddress()).child("status").setValue("Rejected").addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(ApproveRentRequest.this, "Request rejected.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        requests.remove(request);
        Log.d("Reject","done");
        adapter.notifyDataSetChanged();
    }
}