package com.example.rentalagency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    private EditText Email;
    private EditText pwd;
    private Button login;
    private ProgressBar bar;
    Task<AuthResult> auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Email = findViewById(R.id.loginemailedittext);
        pwd = findViewById(R.id.passwordedittext);
        bar = findViewById(R.id.progressBar);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Email.getText().toString().isEmpty() || pwd.getText().toString().isEmpty()){
                    Email.setText("");
                    pwd.setText("");
                    Toast.makeText(SignInActivity.this,"Enter credentials.",Toast.LENGTH_SHORT).show();
                }
                else{
                    bar.setVisibility(View.VISIBLE);
                    auth = FirebaseAuth.getInstance().signInWithEmailAndPassword(Email.getText().toString(),pwd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            bar.setVisibility(View.INVISIBLE);
                            if(task.isSuccessful()){
                                Toast.makeText(SignInActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignInActivity.this,LandingPage.class));
                                finish();
                            }
                            else{
                                Email.setText("");
                                pwd.setText("");
                                Toast.makeText(SignInActivity.this,"Error while logging in.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}