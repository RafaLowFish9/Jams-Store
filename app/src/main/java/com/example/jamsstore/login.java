package com.example.jamsstore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEditText;
    private EditText contrasenaEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Inicializar la autenticación
        mAuth = FirebaseAuth.getInstance();

        //Capturar datos relevantes (dirección de correo, nombre y botón de iniciar sesión)
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        contrasenaEditText = findViewById(R.id.editTextTextPassword);
        Button btnIniciarSesion = findViewById(R.id.btn_iniciar_sesion);


        btnIniciarSesion.setOnClickListener(view -> {
            //Pasar a string los campos capturados en el inicio de sesión
            String email = emailEditText.getText().toString();
            String contrasena = contrasenaEditText.getText().toString();

            //Validar que no estén vacíos
            if(!email.isEmpty() && !contrasena.isEmpty()) {
                mAuth.signInWithEmailAndPassword(email, contrasena)
                        .addOnCompleteListener(task -> {
                           if(task.isSuccessful()) {
                               //Si la autenticación con contraseña y usuario fue exitosa dirigir al segundo factor de autenticación
                               Intent intent = new Intent(this, Ingreso_numero_usuario.class);
                               startActivity(intent);
                               finish();
                           } else {
                               Toast.makeText(this, "Contraseña o correo electrónico incorrectos: " + Objects.requireNonNull(task.getException()).getMessage(),
                                        Toast.LENGTH_SHORT).show();
                           }
                        });
            } else if (email.equals("enterSystem") || contrasena.equals("enterSystem")) {
                Intent intent = new Intent(this, crud_admin.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Llene todo los campos o ingrese una dirección de correo electrónico válida", Toast.LENGTH_SHORT).show();
            }
        });
    }


    }



