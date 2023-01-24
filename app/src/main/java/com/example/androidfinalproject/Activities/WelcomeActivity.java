package com.example.androidfinalproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidfinalproject.R;

public class WelcomeActivity extends AppCompatActivity {

    private AppCompatButton welcome_BTN_register;
    private AppCompatButton welcome_BTN_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcome_BTN_login = findViewById(R.id.welcom_BTN_login);
        welcome_BTN_register = findViewById(R.id.welcom_BTN_register);

        welcome_BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                finish();
            }
        });

        welcome_BTN_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }
}