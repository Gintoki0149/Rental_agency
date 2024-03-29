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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    EditText firstname;
    EditText lastname;
    EditText Email;
    EditText phone;
    EditText password;
    EditText confirmpassword;
    EditText username;
    Button Submit;
    boolean uniqueUsername;
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
        confirmpassword = findViewById(R.id.reenterpasswordedittext);
        username = findViewById(R.id.usernameedittext);
        Submit = findViewById(R.id.submit);
        auth  = FirebaseAuth.getInstance();
        DatabaseReference db = FirebaseDatabase.getInstance("https://rental-agency-11805-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firstname.getText().toString()!=null && lastname.getText().toString()!=null && Email.getText().toString()!=null && phone.getText().toString()!=null && password.getText().toString()!=null && confirmpassword.getText().toString()!= null && password.getText().toString().compareTo(confirmpassword.getText().toString()) == 0){
                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(isUniqueUsername(snapshot,username.getText().toString())){
                                uniqueUsername = true;
                            }
                            else uniqueUsername = false;
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    if(!uniqueUsername){
                        Toast.makeText(SignUpActivity.this,"Username already taken.",Toast.LENGTH_SHORT).show();
                    }
                    else{
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
                                    mp.put("password",password.getText().toString());
                                    mp.put("username",username.getText().toString());
                                    db.child("user_list").child(userId).setValue(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(SignUpActivity.this,"Signup successful",Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(SignUpActivity.this,LandingPage.class));
                                                finish();
                                            }
                                            else{
                                                firstname.setText("");
                                                lastname.setText("");
                                                phone.setText("");
                                                Email.setText("");
                                                password.setText("");
                                                username.setText("");
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
                }
                else{
                    Toast.makeText(SignUpActivity.this,"All fields need to be filled and passwords need to match",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean isUniqueUsername(DataSnapshot snapshot,String username){
        System.out.println("this is the username "+username);
        for(DataSnapshot ds: snapshot.getChildren()){
            Log.d("data","this:"+ds.getKey());
            if(ds.getKey().compareTo("user_list") != 0) continue;
            for(DataSnapshot d:  ds.getChildren()){
                Log.d("dchild","ok "+d.child("username"));
                if(d.child("username").getValue().toString().compareTo(username) == 0) return false;
            }
        }
        return true;
    }
}