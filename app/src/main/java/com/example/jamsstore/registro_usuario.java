package com.example.jamsstore;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.jamsstore.modelos_de_datos.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;

import java.io.Serializable;

public class registro_usuario extends AppCompatActivity {

    // private FirebaseAuth mAuth;


    //EditText

    //Campos formulario de la actividad de registro
    private EditText pais_editText;
    private EditText correo_editText;
    private EditText nombre_editText;
    private EditText apellido_editText;
    private EditText nombre_usuario_editText;
    private EditText contrase_a_editText;

    //Botones
    private Button btn_registro_editText; //Botón de continuar en el formulario de registro


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registro_usuario), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            // ...
            // Initialize Firebase Auth
            //    mAuth = FirebaseAuth.getInstance();

            //Capturar los elementos editText del formulario por su id
            pais_editText = findViewById(R.id.campo_pais);
            correo_editText = findViewById(R.id.campo_correo);
            nombre_editText = findViewById(R.id.campo_nombre);
            apellido_editText = findViewById(R.id.campo_apellido);
            nombre_usuario_editText = findViewById(R.id.campo_nombre_usuario);
            contrase_a_editText = findViewById(R.id.campo_contrase_a);

            //Capturar botones
            btn_registro_editText = findViewById(R.id.button_confirm);


            //Añadir evento listener para el botón de registro
            btn_registro_editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Capturar datos ingresados a cada campo y pasarlos a string
                    String pais = pais_editText.getText().toString();
                    String nombre = nombre_editText.getText().toString();
                    String apellido = apellido_editText.getText().toString();
                    String correo = correo_editText.getText().toString();
                    String contrase_a = contrase_a_editText.getText().toString();
                    String nombre_usuario = nombre_usuario_editText.getText().toString();

                    if ((!pais.isEmpty() && !correo.isEmpty()) && (!nombre.isEmpty() && !apellido.isEmpty()) && !nombre_usuario.isEmpty()) {
                        Usuario usuario = new Usuario(
                                null,
                                pais,
                                nombre,
                                apellido,
                                correo,
                                contrase_a,
                                nombre_usuario,
                                null,
                                null,
                                null,
                                null
                        );

                        Intent intent = new Intent(registro_usuario.this, day_of_birth_activity.class);
                        intent.putExtra("usuario", usuario);
                        startActivity(intent);

                    } else {
                        pais_editText.setError("Rellene el campo");
                    }

                }
            });


            return insets;
        });


        Button btn_continuar = findViewById(R.id.button_confirm);
        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registro_usuario.this, login.class);
                startActivity(intent);
            }
        });
    }
}




    //Al iniciar la actividad, comienza checando si el usuario se encuentra loggeado en la aplicación
//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser usuario_actual = mAuth.getCurrentUser();
//
//        //Revisar si el usuario se encuentra loggeado (no es null)
//        if(usuario_actual != null) {
//
//        }
//    }

    //Si el usuario
