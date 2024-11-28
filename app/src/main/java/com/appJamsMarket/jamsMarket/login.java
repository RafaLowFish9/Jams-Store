package com.appJamsMarket.jamsMarket;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import android.util.Log;

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
            String email = emailEditText.getText().toString().trim();
            String contrasena = contrasenaEditText.getText().toString().trim();

            //Validar que no estén vacíos
            if (!email.isEmpty() && !contrasena.isEmpty()) {
                mAuth.signInWithEmailAndPassword(email, contrasena)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                //Si la autenticación con contraseña y usuario fue exitosa dirigir al segundo factor de autenticación
                                Intent intent = new Intent(this, Ingreso_numero_usuario.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(this, "Contraseña o correo electrónico incorrectos: " + Objects.requireNonNull(task.getException()).getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                Log.d("Mostrar email", "Input: " + email);
                                Log.d("Mostrar contraseña", "Input: " + contrasena);
                            }
                        });
            }  else if (email.equals("enterSystem")  || contrasena.equals("jamsMarket@gmail.com")) {
                Intent intent = new Intent(this, crud_admin.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Llene todo los campos o ingrese una dirección de correo electrónico válida", Toast.LENGTH_SHORT).show();
            }
        });
        Button btnRegistro = findViewById(R.id.botonRegistro);

        btnRegistro.setOnClickListener(view -> {
            Intent abrirRegistro = new Intent(this, registro_usuario.class);
            startActivity(abrirRegistro);
            finish();
        });
    }

}




