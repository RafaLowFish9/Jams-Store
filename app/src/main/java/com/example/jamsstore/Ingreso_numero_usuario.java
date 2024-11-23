package com.example.jamsstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ingreso_numero_usuario extends AppCompatActivity {

    private EditText numeroEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_numero_usuario);

        numeroEditText = findViewById(R.id.inputNumeroCelular);
        Button btnEnviarNumero = findViewById(R.id.btnEnviarNumero);

        btnEnviarNumero.setOnClickListener(view -> {
            String numero = numeroEditText.getText().toString();
            if(!numero.isEmpty()) {
                //Enviar el número a la actividad de autenticación
                Intent intent = new Intent(this, authentication.class);
                intent.putExtra("NUMERO_CELULAR", numero); //Envía el número
                startActivity(intent);
            } else {
                Toast.makeText(this, "Ingrese un número válido", Toast.LENGTH_SHORT).show();
            }
        });

    }
}