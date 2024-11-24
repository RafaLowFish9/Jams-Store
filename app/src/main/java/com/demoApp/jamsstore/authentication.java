package com.demoApp.jamsstore;

import android.os.Bundle;
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
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class authentication extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String idVerificacion;
    private String numeroTelefono;


    //Input del código de verificación
    private EditText digito1;
    private EditText digito2;
    private EditText digito3;
    private EditText digito4;
    private EditText digito5;
    private EditText digito6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        mAuth = FirebaseAuth.getInstance();
        //Obtener el número de teléfono introducido en la actividad anterior
        numeroTelefono = getIntent().getStringExtra("NUMERO_CELULAR");

        digito1 = findViewById(R.id.digito1);
        digito2 = findViewById(R.id.digito2);
        digito3 = findViewById(R.id.digito3);
        digito4 = findViewById(R.id.digito4);
        digito5 = findViewById(R.id.digito5);
        digito6 = findViewById(R.id.digito6);

        List<EditText> listaCodigoSMS = new ArrayList<>();
        listaCodigoSMS.add(digito1);
        listaCodigoSMS.add(digito2);
        listaCodigoSMS.add(digito3);
        listaCodigoSMS.add(digito4);
        listaCodigoSMS.add(digito5);
        listaCodigoSMS.add(digito6);


        Button btnVerificar = findViewById(R.id.button_confirm);

        if(numeroTelefono != null){
            enviarCodigoDeVerificacion(numeroTelefono);
        } else {
            Toast.makeText(this, "Número de teléfono no encontrado", Toast.LENGTH_SHORT).show();
        }

        //Verificar que el usuario haya rellenado el campo al presionar el botón de confirmar código
        btnVerificar.setOnClickListener(view -> {
            String codigo = obtenerInputUsuario(listaCodigoSMS);
            if (!codigo.isEmpty()) {
                //Verificar que el código sea correcto
                verificarCodigo(codigo);
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
                        .setTimeout(180L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Toast.makeText(authentication.this, "Verificación éxitosa", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(authentication.this, "Verificación fallida" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onCodeSent(@NonNull String idVerificacion, @NonNull PhoneAuthProvider.ForceResendingToken token){
                                Toast.makeText(authentication.this, "Codigo enviado a: " + numeroCelular, Toast.LENGTH_SHORT).show();
                            }
                        }
                        )
                        .build();
                PhoneAuthProvider.verifyPhoneNumber(opciones);
                //Fin de la autenticación por celular
    }

    private void verificarCodigo(String codigo) {
        PhoneAuthCredential credencial = PhoneAuthProvider.getCredential(idVerificacion, codigo);
        Objects.requireNonNull(mAuth.getCurrentUser()).linkWithCredential(credencial)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        Toast.makeText(this, "La autenticación fue éxitosa", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Autenticación fallida" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}