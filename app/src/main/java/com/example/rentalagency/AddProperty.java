package com.example.rentalagency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddProperty extends AppCompatActivity {
    private EditText address;
    private EditText city;
    private EditText state;
    private EditText rentpm;
    private EditText annualhike;
    private EditText bedrooms;
    private EditText floors;
    private EditText year_of_construction;
    private EditText pincode;
    private TextView ownerusername;
    private Button addbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);
        address = findViewById(R.id.addressedittext);
        city = findViewById(R.id.cityedittext);
        state = findViewById(R.id.stateedittext);
        rentpm = findViewById(R.id.rentedittext);
        annualhike = findViewById(R.id.annualhikeedittext);
        bedrooms = findViewById(R.id.bedroomsedittext);
        floors = findViewById(R.id.floorsedittext);
        year_of_construction = findViewById(R.id.yearofconstructionedittext);
        pincode = findViewById(R.id.pincodeedittext);
        ownerusername = findViewById(R.id.ownerusernametextview);
        addbutton = findViewById(R.id.add);
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://rental-agency-11805-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference dref = db.getReference();
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ownerusername.setText(snapshot.child("user_list").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("username").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                String addr =address.getText().toString();
                String c = city.getText().toString();
                String s = state.getText().toString();
                String rent = rentpm.getText().toString();
                String hike = annualhike.getText().toString();
                String beds = bedrooms.getText().toString();
                String floor = floors.getText().toString();
                String yoc = year_of_construction.getText().toString();
                String pin = pincode.getText().toString();
                if(allClear(addr, c, s, rent, hike, beds, floor, yoc, pin)){
                    HashMap<String,Object>mp = new HashMap<>();
                    mp.put("Address",addr);
                    mp.put("City",c);
                    mp.put("State",s);
                    mp.put("Rent",rent);
                    mp.put("Hike",hike);
                    mp.put("Bedrooms",beds);
                    mp.put("Floors",floor);
                    mp.put("Year Of Construction",yoc);
                    mp.put("Pincode",pin);
                    mp.put("Owner username",ownerusername.getText().toString());
                    mp.put("Availability","available");
                    dref.child("properties").child(userId).child(addr).setValue(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AddProperty.this,"Property added successfully.",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddProperty.this,LandingPage.class));
                                finish();
                            }
                            else Toast.makeText(AddProperty.this,"Error while adding property. Retry",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else Toast.makeText(AddProperty.this,"All fields need to be filled.",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public boolean allClear(String add, String c, String s, String rent, String hike, String beds, String floor, String yoc, String pin){
        if(add.isEmpty() || c.isEmpty() || s.isEmpty() || rent.isEmpty() || hike.isEmpty() || beds.isEmpty() || floor.isEmpty() || yoc.isEmpty() || pin.isEmpty()){
            return false;
        }
        return true;
    }
}