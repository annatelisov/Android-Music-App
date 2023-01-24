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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText login_TXT_email;
    private EditText login_TXT_password;
    private AppCompatButton login_BTN_loginbutton;
    private TextView login_TXT_registernow;
    private String email;
    private String password;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        mAuth = FirebaseAuth.getInstance();

        login_BTN_loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
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

    private void loginUser() {
        email = login_TXT_email.getText().toString();
        password = login_TXT_password.getText().toString();
        if(TextUtils.isEmpty(email)){
            login_TXT_email.setError("Email is empty");
            login_TXT_email.requestFocus();
            return;
        }else if(TextUtils.isEmpty(password)){
            login_TXT_password.setError("Password is empty");
            login_TXT_password.requestFocus();
            return;
        }else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"User logged successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this,"User login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void findViews(){
        login_TXT_email = findViewById(R.id.login_TXT_email);
        login_TXT_password = findViewById(R.id.login_TXT_password);
        login_BTN_loginbutton = findViewById(R.id.login_BTN_loginbutton);
        login_TXT_registernow = findViewById(R.id.login_TXT_registernow);
    }

}