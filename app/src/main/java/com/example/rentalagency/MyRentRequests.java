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
import java.util.HashMap;

public class MyRentRequests extends AppCompatActivity implements RemoveRequest{
    DatabaseReference db;
    ArrayList<Request>requests;
    MyRentRequestsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rent_requests);
        Log.d("MyRentRequests","started");
        db = FirebaseDatabase.getInstance("https://rental-agency-11805-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("rent_requests");
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMyRequests);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requests = new ArrayList<>();
        adapter = new MyRentRequestsAdapter(getApplicationContext(),requests,this);
        recyclerView.setAdapter(adapter);
        db.child("from").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("This is snapshot.getKey:"+snapshot.getChildrenCount());
                System.out.println("This is snapshot.getchildren:"+snapshot.getChildren());
                requests.clear();
                for(DataSnapshot snap:snapshot.getChildren()){  //senders
                    Log.d("rhs","receiver: "+LandingPage.curuser+" sngp.getKey(): "+snap.getKey());
                    if(snap.getKey().toString().compareTo(LandingPage.curuser) == 0){
                        for(DataSnapshot ds:snap.getChildren()){    //receivers
                            for(DataSnapshot s: ds.getChildren()){  //address
                                requests.add(new Request(LandingPage.curuser,ds.getKey(),s.child("request").getValue().toString(),s.getKey(),s.child("status").getValue().toString()));
                                System.out.println("This is the message: "+s.getValue().toString()+" this is the key:"+ds.getKey().toString());
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                Log.d("MyRentRequests","woorking");
                System.out.println(requests.size());
                for(Request request:requests){
                    System.out.println(request.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("myrentrequests","F");
            }
        });
    }

    @Override
    public void remove(Request request) {
        String to = request.getTo();
        String from = request.getFrom();
        String message = request.getMessage();
        int len = from.length()+(" has requested to rent the property at Address : ".length());
        System.out.println("this is the substring:"+request.getMessage().substring(len)+"$");
        db.child("from").child(from).child(to).child(request.getAddress()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                System.out.println("This is the address substring: "+request.getMessage().substring(len,len+request.getAddress().length()));
                Toast.makeText(MyRentRequests.this,"Request removed",Toast.LENGTH_SHORT).show();
            }
        });
        System.out.println(message+"//Request Removed");
        if(request.getStatus().compareTo("Pending") == 0) {
            db.child("to").child(to).child(from).child(request.getAddress()).child("status").setValue("Removed");
        }
        requests.remove(request);
        adapter.notifyDataSetChanged();
    }
}