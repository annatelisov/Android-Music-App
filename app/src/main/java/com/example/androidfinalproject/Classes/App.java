package com.example.androidfinalproject.Classes;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MyImageUtils.initHelper(this);
    }
}
