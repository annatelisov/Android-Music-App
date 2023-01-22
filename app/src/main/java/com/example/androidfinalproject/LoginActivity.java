package com.example.androidfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText login_TXT_username;
    private EditText login_TXT_password;
    private AppCompatButton login_BTN_loginbutton;
    private TextView login_TXT_registernow;
    private String username;
    private String password;
    private String getPassword;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://androidfinalproject-23-default-rtdb.firebaseio.com/users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();

        login_BTN_loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
                if(username.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter your username", Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(username)){
                                getPassword = snapshot.child(username).child(password).getValue(String.class);
                                if(getPassword.equals(password)){
                                    Toast.makeText(LoginActivity.this, "User login successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }
                                else{
                                    Toast.makeText(LoginActivity.this, "Wrong password, please try again", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Username not exist", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        login_TXT_registernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

    }

    private void findViews(){
        login_TXT_username = findViewById(R.id.login_TXT_username);
        login_TXT_password = findViewById(R.id.login_TXT_password);
        login_BTN_loginbutton = findViewById(R.id.login_BTN_loginbutton);
        login_TXT_registernow = findViewById(R.id.login_TXT_registernow);
    }

    private void init(){
        username = login_TXT_username.getText().toString();
        password = login_TXT_password.getText().toString();
    }
}