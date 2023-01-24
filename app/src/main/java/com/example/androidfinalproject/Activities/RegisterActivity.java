package com.example.androidfinalproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidfinalproject.R;
import com.example.androidfinalproject.Classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText register_TXT_username;
    private EditText register_TXT_email;
    private EditText register_TXT_phone;
    private EditText register_TXT_password;
    private AppCompatButton register_BTN_registerbutton;
    private TextView register_TXT_loginhere;
    private String email, phone, password, username;
    FirebaseAuth mAuth;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViews();
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        register_BTN_registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

        register_TXT_loginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

    private void createUser() {
        username = register_TXT_username.getText().toString();
        email = register_TXT_email.getText().toString();
        phone = register_TXT_phone.getText().toString();
        password = register_TXT_password.getText().toString();
        if(TextUtils.isEmpty(username)){
            register_TXT_username.setError("Name is empty");
            register_TXT_username.requestFocus();
            return;
        }else if(TextUtils.isEmpty(phone)){
            register_TXT_phone.setError("Phone number is empty");
            register_TXT_phone.requestFocus();
            return;
        }else if(TextUtils.isEmpty(email)){
            register_TXT_email.setError("Email is empty");
            register_TXT_email.requestFocus();
            return;
        }else if(TextUtils.isEmpty(password)){
            register_TXT_password.setError("Password is empty");
            register_TXT_password.requestFocus();
            return;
        }else if(phone.length() < 9){
            register_TXT_phone.setError("Too short phone number");
            register_TXT_phone.requestFocus();
            return;
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        User user = new User(username, email, password, phone);
                        db.getReference("Users").child(mAuth.getCurrentUser().getUid()).setValue(user);
                        Toast.makeText(RegisterActivity.this,"User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this,"Error on registration: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void findViews(){
        register_TXT_username = findViewById(R.id.register_TXT_username);
        register_TXT_email = findViewById(R.id.register_TXT_email);
        register_TXT_phone = findViewById(R.id.register_TXT_phone);
        register_TXT_password = findViewById(R.id.register_TXT_password);
        register_BTN_registerbutton = findViewById(R.id.register_BTN_registerbutton);
        register_TXT_loginhere = findViewById(R.id.register_TXT_loginhere);
    }

}