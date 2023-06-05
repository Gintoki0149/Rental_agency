package com.example.rentalagency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LandingPage extends AppCompatActivity {
    private TextView logouttextview;
    private TextView addproperty;
    private TextView rentproperty;
    private TextView approverentrequests;
    private TextView myrentrequests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        logouttextview = findViewById(R.id.logouttv);
        addproperty = findViewById(R.id.addpropertytextview);
        rentproperty = findViewById(R.id.rentpropertytextview);
        approverentrequests = findViewById(R.id.approverentrequeststextview);
        myrentrequests = findViewById(R.id.myrentrequeststextview);

        addproperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this,AddProperty.class));
            }
        });
        logouttextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(LandingPage.this,"Successfully logged out",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LandingPage.this,MainActivity.class));
                finish();
            }
        });
    }
}