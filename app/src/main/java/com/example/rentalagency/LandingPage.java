package com.example.rentalagency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class LandingPage extends AppCompatActivity {
    private TextView welcometext;
    private TextView logouttextview;
    private TextView addproperty;
    private TextView rentproperty;
    private TextView approverentrequests;
    private TextView myrentrequests;
    private String curuser;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference db = FirebaseDatabase.getInstance("https://rental-agency-11805-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        welcometext = findViewById(R.id.welcome);
        logouttextview = findViewById(R.id.logouttv);
        addproperty = findViewById(R.id.addpropertytextview);
        rentproperty = findViewById(R.id.rentpropertytextview);
        approverentrequests = findViewById(R.id.approverentrequeststextview);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                curuser = snapshot.child("user_list").child(auth.getCurrentUser().getUid().toString()).child("username").getValue().toString();
                welcometext.setText("Welcome "+curuser+"!!");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myrentrequests = findViewById(R.id.myrentrequeststextview);
        addproperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this,AddProperty.class));
            }
        });
        rentproperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this,RentProperty.class));
            }
        });
        logouttextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.getInstance().signOut();
                Toast.makeText(LandingPage.this,"Successfully logged out",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LandingPage.this,MainActivity.class));
                finish();
            }
        });
    }
}