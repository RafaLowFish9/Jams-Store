package com.example.jamsstore;

import android.app.Application;
import com.google.firebase.FirebaseApp;



public class JamsStore extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Inicializar firebase una sola vez
        FirebaseApp.initializeApp(this);
    }

}
