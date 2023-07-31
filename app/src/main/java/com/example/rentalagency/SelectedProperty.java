package com.example.rentalagency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectedProperty extends AppCompatActivity {
    TextView address,availability,bedrooms,city,floors,hike,owner,pincode,rent,state,yoc;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_property);
        button = findViewById(R.id.rent);
        DatabaseReference db = FirebaseDatabase.getInstance("https://rental-agency-11805-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        System.out.println("This is the pincode:"+getIntent().getIntExtra("Pincode",000000));
        address = findViewById(R.id.selectedpropertyaddress);
        availability = findViewById(R.id.selectedpropertyavailability);
        bedrooms = findViewById(R.id.selectedpropertyberooms);
        city = findViewById(R.id.selectedpropertycity);
        floors = findViewById(R.id.selectedpropertyfloors);
        hike = findViewById(R.id.selectedpropertyhike);
        owner = findViewById(R.id.selectedpropertyowner);
        pincode = findViewById(R.id.selectedpropertypincode);
        rent = findViewById(R.id.selectedpropertyrent);
        state = findViewById(R.id.selectedpropertystate);
        yoc = findViewById(R.id.selectedpropertyyoc);
        address.setText("Address : "+getIntent().getStringExtra("Address"));
        availability.setText("Availability : "+getIntent().getStringExtra("Availability"));
        bedrooms.setText("Bedrooms : "+String.valueOf(getIntent().getIntExtra("Bedrooms",0)));
        city.setText("City : "+getIntent().getStringExtra("City"));
        floors.setText("Floors : "+String.valueOf(getIntent().getIntExtra("Floors",1)));
        hike.setText("Annual Rent Hike% : "+String.valueOf(getIntent().getIntExtra("Hike",0)));
        owner.setText("Owner : "+getIntent().getStringExtra("Owner Username"));
        pincode.setText("Pin code : "+String.valueOf(getIntent().getIntExtra("Pincode",000000)));
        rent.setText("Rent(monthly) : "+String.valueOf(getIntent().getIntExtra("Rent",0)));
        state.setText("State : "+getIntent().getStringExtra("State"));
        yoc.setText("Year Of Construction : "+getIntent().getStringExtra("Year Of Construction"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.child("rent_requests").child("from").child(LandingPage.curuser).child(owner.getText().toString().substring(8)).child(address.getText().toString().substring(10)).setValue(LandingPage.curuser+" has requested to rent the property at "+address.getText().toString());
                db.child("rent_requests").child("to").child(owner.getText().toString().substring(8)).child(LandingPage.curuser).child(address.getText().toString().substring(10)).setValue(LandingPage.curuser+" has requested to rent the property at "+address.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SelectedProperty.this,"Request sent",Toast.LENGTH_SHORT).show();
                    }
                });
                startActivity(new Intent(SelectedProperty.this,MyRentRequests.class));
                finish();
            }
        });
    }
}