package com.example.rentalagency;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class RentProperty extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_property);
        DatabaseReference ref = FirebaseDatabase.getInstance("https://rental-agency-11805-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("properties");
        RecyclerView rentPropertyRecyclerView = findViewById(R.id.rentpropertyrecyclerView);
        rentPropertyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Property>properties = new ArrayList<>();
        RentPropertyAdapter adapter = new RentPropertyAdapter(getApplicationContext(),properties);
        rentPropertyRecyclerView.setAdapter(adapter);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    if(dataSnapshot.getKey() == FirebaseAuth.getInstance().getUid()) continue;
                    Log.d("msg","this works");
                    Log.d("msg",dataSnapshot.toString());
                    String date = dataSnapshot.child("Year Of Construction").getValue().toString();
                    Date d = null;
                    try {
                        d = new SimpleDateFormat("dd/mm/yyyy")
                                .parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Property property = new Property(
                            dataSnapshot.child("Adress").getValue().toString(),
                            parseInt(dataSnapshot.child("Bedrooms").getValue().toString()),
                            dataSnapshot.child("City").getValue().toString(),
                            parseInt(dataSnapshot.child("Floors").getValue().toString()),
                            parseInt(dataSnapshot.child("Hike").getValue().toString()),
                            dataSnapshot.child("Owner username").getValue().toString(),
                            parseInt(dataSnapshot.child("Pincode").getValue().toString()),
                            parseInt(dataSnapshot.child("Rent").getValue().toString()),
                            dataSnapshot.child("State").getValue().toString(),
                            d
                    );
                    properties.add(property);
                    System.out.println(properties.size());
                    for (Property p :properties){
                        System.out.println(p.toString());
                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}