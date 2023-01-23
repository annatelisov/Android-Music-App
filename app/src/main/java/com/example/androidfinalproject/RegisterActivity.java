package com.example.androidfinalproject;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText register_TXT_name;
    private EditText register_TXT_email;
    private EditText register_TXT_phone;
    private EditText register_TXT_password;
    private AppCompatButton register_BTN_registerbutton;
    private TextView register_TXT_loginhere;
    private String email, phone, password, name;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViews();
        mAuth = FirebaseAuth.getInstance();
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
        name = register_TXT_name.getText().toString();
        email = register_TXT_email.getText().toString();
        phone = register_TXT_phone.getText().toString();
        password = register_TXT_password.getText().toString();
        if(TextUtils.isEmpty(name)){
            register_TXT_name.setError("Name is empty");
            register_TXT_name.requestFocus();
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
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        User user = new User(name, email, password, phone);
                        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getEmail()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this,"User registered successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                }
                            }
                        });
                    }else{
                        Toast.makeText(RegisterActivity.this,"Error on registration: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void findViews(){
        register_TXT_name = findViewById(R.id.register_TXT_name);
        register_TXT_email = findViewById(R.id.register_TXT_email);
        register_TXT_phone = findViewById(R.id.register_TXT_phone);
        register_TXT_password = findViewById(R.id.register_TXT_password);
        register_BTN_registerbutton = findViewById(R.id.register_BTN_registerbutton);
        register_TXT_loginhere = findViewById(R.id.register_TXT_loginhere);
    }

}