package com.demoApp.jamsstore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class authentication extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String idVerificacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        mAuth = FirebaseAuth.getInstance();
        //Obtener el número de teléfono introducido en la actividad anterior
        String numeroTelefono = getIntent().getStringExtra("NUMERO_CELULAR");

        //Input del código de verificación
        EditText digito1 = findViewById(R.id.digito1);
        EditText digito2 = findViewById(R.id.digito2);
        EditText digito3 = findViewById(R.id.digito3);
        EditText digito4 = findViewById(R.id.digito4);
        EditText digito5 = findViewById(R.id.digito5);
        EditText digito6 = findViewById(R.id.digito6);

        //LIsta de inputs para pasarlos a un solo string utilizando: obtenerInputUsuario()
        List<EditText> listaCodigoSMS = new ArrayList<>();
        listaCodigoSMS.add(digito1);
        listaCodigoSMS.add(digito2);
        listaCodigoSMS.add(digito3);
        listaCodigoSMS.add(digito4);
        listaCodigoSMS.add(digito5);
        listaCodigoSMS.add(digito6);

        //Botón para verificar el código
        Button btnVerificar = findViewById(R.id.button_confirm);
        //Enviar código
        enviarCodigoDeVerificacion(numeroTelefono);

        //Verificar que el usuario haya rellenado el campo al presionar el botón de confirmar código
        btnVerificar.setOnClickListener(view -> {
            String codigo = obtenerInputUsuario(listaCodigoSMS);
            if (!codigo.isEmpty()) {
                //Verificar que el código sea correcto
                verificarCodigo(codigo);
                Intent intent = new Intent(this, inicio.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Por favor digite el código de 6 digitos enviado por SMS", Toast.LENGTH_SHORT).show();
            }

        });
    }


    public String obtenerInputUsuario(List<EditText> listaDigitos) {
        StringBuilder codigo = new StringBuilder();
        for(EditText editText : listaDigitos) {
            if(editText != null) {
                String digito = editText.getText().toString().trim();
                codigo.append(digito);
            }
        }
        return codigo.toString().trim();
    }

    private void enviarCodigoDeVerificacion(String numeroCelular) {
        PhoneAuthOptions opciones =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(numeroCelular)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                //Verificación éxitosa
                                Toast.makeText(authentication.this, "Verificación éxitosa", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Log.e("VerificacionSMS", "Error: " + e.getMessage());
                                Toast.makeText(authentication.this, "Error al enviar código: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onCodeSent(@NonNull String idVerificacion, @NonNull PhoneAuthProvider.ForceResendingToken token){
                                authentication.this.idVerificacion = idVerificacion;
                                Toast.makeText(authentication.this, "Código enviado", Toast.LENGTH_SHORT).show();
                            }
                        }
                        )
                        .build();
                PhoneAuthProvider.verifyPhoneNumber(opciones);
                //Fin de la autenticación por celular
    }

    private void verificarCodigo(String codigo) {
        PhoneAuthCredential credencial = PhoneAuthProvider.getCredential(idVerificacion, codigo);
        iniciarSesionConCredencialTelefono(credencial);
    }

    private void iniciarSesionConCredencialTelefono(PhoneAuthCredential credencial) {
        mAuth.signInWithCredential(credencial)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(authentication.this, "Autenticación exitosa", Toast.LENGTH_SHORT).show();
                        // Redirigir al usuario a la actividad principal
                        Intent intent = new Intent(this, inicio.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(authentication.this, "Error de autenticación", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}