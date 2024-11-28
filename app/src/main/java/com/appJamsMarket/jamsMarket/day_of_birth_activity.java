package com.appJamsMarket.jamsMarket;


import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.appJamsMarket.jamsMarket.modelos_de_datos.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class day_of_birth_activity extends AppCompatActivity {
    private EditText fecha_nacimiento_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_of_birth);

        fecha_nacimiento_editText = findViewById(R.id.campo_fecha_nacimiento);

        Button btn_guardar_fecha = findViewById(R.id.btn_continuar_fecha_nacimiento);
        btn_guardar_fecha.setOnClickListener(view -> {
            String fecha_nacimiento = fecha_nacimiento_editText.getText().toString();
            if(!fecha_nacimiento.isEmpty()){
                Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");
                assert usuario != null;
                usuario.setFecha_nacimiento(fecha_nacimiento);


                FirebaseFirestore baseDeDatos = FirebaseFirestore.getInstance();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();

                baseDeDatos.collection("Usuarios")
                        .add(usuario)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "Documento usuarios añadido con ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error al añadir el documento: " + e);
                            }
                        });
                mAuth.createUserWithEmailAndPassword(usuario.getCorreo_electronico(), usuario.getContrase_a())
                        .addOnCompleteListener(this, task -> {
                            if(task.isSuccessful()) {
                                Log.d("Registro", "EL registro de usuario fue éxitoso, revise la consola de firebase");
                            } else {
                                Log.d("Error registro", "Algo salió mal, revise el registro de usuario para más información.");
                            }
                        });

                Intent intent = new Intent(day_of_birth_activity.this, inicio.class);
                startActivity(intent);

            }
        });
    }
}