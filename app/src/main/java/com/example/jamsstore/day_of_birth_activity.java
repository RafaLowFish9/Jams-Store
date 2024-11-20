package com.example.jamsstore;


import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jamsstore.modelos_de_datos.Usuario;
import com.example.jamsstore.registro_usuario;
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
                Intent intent = new Intent(day_of_birth_activity.this, inicio.class);
                startActivity(intent);

            }
        });
    }
}