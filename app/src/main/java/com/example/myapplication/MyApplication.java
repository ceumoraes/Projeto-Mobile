package com.example.myapplication;

import android.app.Application;
import com.google.firebase.FirebaseApp;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Inicializa o Firebase
        FirebaseApp.initializeApp(this);
    }
}