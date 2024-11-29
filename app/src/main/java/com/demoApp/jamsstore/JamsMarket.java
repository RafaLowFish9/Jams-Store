package com.demoApp.jamsstore;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;


public class JamsMarket extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyApplication", "Clase MyApplication inicializada correctamente");
        // Inicializar firebase una sola vez
        FirebaseApp.initializeApp(this);

        //Establece el idioma a español
        FirebaseAuth.getInstance().setLanguageCode("es");

        //Habilita la renovación automática de del token de app check
        FirebaseAppCheck.getInstance().setTokenAutoRefreshEnabled(true);

        //COnfigura el servicio de app check play integrity (modo depupración)
//        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
//        firebaseAppCheck.installAppCheckProviderFactory(
//                DebugAppCheckProviderFactory.getInstance() // Modo depuración
//        );

        //Configura el servcio de app check play integrity en modo estricto (Producción)
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                PlayIntegrityAppCheckProviderFactory.getInstance());
    }

}
