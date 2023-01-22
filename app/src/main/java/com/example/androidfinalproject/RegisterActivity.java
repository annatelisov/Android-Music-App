package com.example.androidfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private EditText register_TXT_name;
    private EditText register_TXT_phone;
    private EditText register_TXT_username;
    private EditText register_TXT_password;
    private EditText register_TXT_confirmpassword;
    private AppCompatButton register_BTN_registerbutton;
    private TextView register_TXT_loginhere;
    private String name;
    private String phone;
    private String username;
    private String password;
    private String cPassword;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://androidfinalproject-23-default-rtdb.firebaseio.com/users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViews();

        register_BTN_registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
                if(name.isEmpty() || phone.isEmpty() || username.isEmpty() || password.isEmpty() || cPassword.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please enter all your details", Toast.LENGTH_SHORT).show();
                }
                else if(!(password.equals(cPassword))){
                    Toast.makeText(RegisterActivity.this, "Your passwords are not matching", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(username)){
                                Toast.makeText(RegisterActivity.this, "This username is already exist, please login", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                databaseReference.child(username).child("name").setValue(name);
                                databaseReference.child(username).child("phone").setValue(phone);
                                databaseReference.child(username).child("password").setValue(password);

                                Toast.makeText(RegisterActivity.this, "User " + username + " registered successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.w("pttt", "Failed to read value.", error.toException());
                        }
                    });
                }
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

    private void findViews(){
        register_TXT_name = findViewById(R.id.register_TXT_name);
        register_TXT_phone = findViewById(R.id.register_TXT_phone);
        register_TXT_username = findViewById(R.id.register_TXT_username);
        register_TXT_password = findViewById(R.id.register_TXT_password);
        register_TXT_confirmpassword = findViewById(R.id.register_TXT_confirmpassword);
        register_BTN_registerbutton = findViewById(R.id.register_BTN_registerbutton);
        register_TXT_loginhere = findViewById(R.id.register_TXT_loginhere);
    }

    private void init(){
        name = register_TXT_name.getText().toString();
        phone = register_TXT_phone.getText().toString();
        username = register_TXT_username.getText().toString();
        password = register_TXT_password.getText().toString();
        cPassword = register_TXT_confirmpassword.getText().toString();
    }
}