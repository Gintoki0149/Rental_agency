package com.example.rentalagency;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

public class RentProperty extends AppCompatActivity implements PropertyClicked{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_property);
        DatabaseReference ref = FirebaseDatabase.getInstance("https://rental-agency-11805-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("properties");
        RecyclerView rentPropertyRecyclerView = findViewById(R.id.rentpropertyrecyclerView);
        rentPropertyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Property>properties = new ArrayList<>();
        RentPropertyAdapter adapter = new RentPropertyAdapter(getApplicationContext(),properties,this);
        rentPropertyRecyclerView.setAdapter(adapter);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                properties.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    if(dataSnapshot.getKey() == FirebaseAuth.getInstance().getUid()) continue;
                    Log.d("msg","this works");
                    Log.d("msg",dataSnapshot.toString());
                    for(DataSnapshot snap:dataSnapshot.getChildren()){
                        String date = snap.child("Year Of Construction").getValue().toString();
                        Date d = null;
                        try {
                            d = new SimpleDateFormat("dd/mm/yyyy")
                                    .parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Property property = new Property(
                                snap.child("Address").getValue().toString(),
                                parseInt(snap.child("Bedrooms").getValue().toString()),
                                snap.child("City").getValue().toString(),
                                parseInt(snap.child("Floors").getValue().toString()),
                                parseInt(snap.child("Hike").getValue().toString()),
                                snap.child("Owner username").getValue().toString(),
                                parseInt(snap.child("Pincode").getValue().toString()),
                                parseInt(snap.child("Rent").getValue().toString()),
                                snap.child("State").getValue().toString(),
                                d,
                                snap.child("Availability").getValue().toString()
                        );
                        properties.add(property);
                        System.out.println(properties.size());
                        for (Property p :properties){
                            System.out.println(p.getPincode());
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
    public void onClick(Property property) {
        System.out.println("property: "+property.getPincode());
        Intent intent = new Intent(RentProperty.this,SelectedProperty.class);
        intent.putExtra("Address",property.getAddress());
        intent.putExtra("Availability",property.getStatus());
        intent.putExtra("Bedrooms",property.getBedrooms());
        intent.putExtra("City",property.getCity());
        intent.putExtra("Floors",property.getFloors());
        intent.putExtra("Hike",property.getHike());
        intent.putExtra("Owner Username",property.getOwner());
        intent.putExtra("Pincode",property.getPincode());
        intent.putExtra("Rent",property.getRent());
        intent.putExtra("State",property.getState());
        intent.putExtra("Address",property.getAddress());
        intent.putExtra("Year Of Construction",property.getYoc().toString().substring(4,10)+property.getYoc().toString().substring(29));
        startActivity(intent);
    }
}