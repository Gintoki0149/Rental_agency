package com.example.rentalagency;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    EditText firstname;
    EditText lastname;
    EditText Email;
    EditText phone;
    EditText password;
    Button Submit;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firstname = findViewById(R.id.firstnameedittext);
        lastname = findViewById(R.id.lastnameedittext);
        Email = findViewById(R.id.emailedittext);
        phone = findViewById(R.id.phonenumberedittext);
        password = findViewById(R.id.pwdedittext);
        Submit = findViewById(R.id.submit);
        auth  = FirebaseAuth.getInstance();
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firstname.getText()!=null && lastname.getText()!=null && Email.getText()!=null && phone.getText()!=null && password.getText()!=null){
                    auth.createUserWithEmailAndPassword(Email.getText().toString(),password.getText().toString()).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String userId = auth.getCurrentUser().getUid();
                                HashMap<String,Object> mp = new HashMap<>();
                                mp.put("firstname",firstname.getText().toString());
                                mp.put("lastname",lastname.getText().toString());
                                mp.put("phone",phone.getText().toString());
                                mp.put("email",Email.getText().toString());
                                FirebaseDatabase.getInstance("https://rental-agency-11805-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("userList").child(userId).setValue(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(SignUpActivity.this,"Signup successful",Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(SignUpActivity.this,LandingPage.class));
                                            finish();
                                        }
                                        else{
                                            Toast.makeText(SignUpActivity.this,"Couldn't signup",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(SignUpActivity.this,"Couldn't signup",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(SignUpActivity.this,"All fields need to be filled.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}