package com.example.jamsstore;

import android.app.Application;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;


public class JamsStore extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Inicializar firebase una sola vez
        FirebaseApp.initializeApp(this);
        FirebaseAuth.getInstance().setLanguageCode("es");
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                PlayIntegrityAppCheckProviderFactory.getInstance());
    }

}
