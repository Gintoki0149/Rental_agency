package com.example.rentalagency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
    private EditText ownerusername;
    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

    }
}