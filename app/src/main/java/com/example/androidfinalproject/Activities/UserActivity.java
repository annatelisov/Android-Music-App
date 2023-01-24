package com.example.androidfinalproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidfinalproject.Classes.User;
import com.example.androidfinalproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserActivity extends AppCompatActivity {

    private TextView user_TXT_name;
    private AppCompatButton user_BTN_change;
    private AppCompatButton user_BTN_back;
    private AppCompatButton user_BTN_logout;
    private LinearLayoutCompat user_LINEAR_changewindow;
    private EditText user_TXT_username;
    private EditText user_TXT_phone;
    private AppCompatButton user_BTN_updatebutton;
    private AppCompatImageView user_IMG_cancel;
    FirebaseAuth mAuth;
    FirebaseDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        findViews();
        user_BTN_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_LINEAR_changewindow.setVisibility(View.VISIBLE);
                user_BTN_updatebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setUpUserDetails();
                        user_LINEAR_changewindow.setVisibility(View.INVISIBLE);
                    }
                });

                user_IMG_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user_LINEAR_changewindow.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });


        user_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, MainActivity.class));
            }
        });

        user_BTN_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(UserActivity.this, WelcomeActivity.class));
            }
        });
    }


    private void findViews(){
        user_TXT_name = findViewById(R.id.user_TXT_name);
        user_BTN_change = findViewById(R.id.user_BTN_change);
        user_BTN_back = findViewById(R.id.user_BTN_back);
        user_BTN_logout = findViewById(R.id.user_BTN_logout);
        user_LINEAR_changewindow = findViewById(R.id.user_LINEAR_changewindow);
        user_TXT_username = findViewById(R.id.user_TXT_username);
        user_TXT_phone = findViewById(R.id.user_TXT_phone);
        user_BTN_updatebutton = findViewById(R.id.user_BTN_updatebutton);
        user_IMG_cancel = findViewById(R.id.user_IMG_cancel);
    }

    private void setUpUserDetails(){
        DatabaseReference ref = db.getReference("Users");
        ref.child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                String username = user_TXT_username.getText().toString();
                String phone = user_TXT_phone.getText().toString();
                if(!TextUtils.isEmpty(username)) {
                    user.setUsername(username);
                }
                if(!TextUtils.isEmpty(phone)) {
                    user.setPhone(phone);
                }
                ref.child(mAuth.getCurrentUser().getUid()).setValue(user);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("pttt", "Failed to read value.", error.toException());
            }
        });
    }
}