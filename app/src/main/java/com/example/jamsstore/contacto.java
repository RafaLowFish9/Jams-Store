package com.example.jamsstore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class contacto extends AppCompatActivity {

    private EditText etNombreUsuario, etCorreoUsuario, etDescripcionProblema;
    private Button btnEnviarSolicitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacto);

        // Configuración para el diseño EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.contacto), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencias a los elementos del formulario
        etNombreUsuario = findViewById(R.id.etNombreUsuario);
        etCorreoUsuario = findViewById(R.id.etCorreoUsuario);
        etDescripcionProblema = findViewById(R.id.etDescripcionProblema);
        btnEnviarSolicitud = findViewById(R.id.btnEnviarSolicitud);

        // Configuración del botón de envío
        btnEnviarSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarSolicitud();
            }
        });
    }

    private void enviarSolicitud() {
        // Obtener los valores de los campos de texto
        String nombre = etNombreUsuario.getText().toString();
        String correo = etCorreoUsuario.getText().toString();
        String descripcion = etDescripcionProblema.getText().toString();

        // Validación básica de campos vacíos
        if (nombre.isEmpty() || correo.isEmpty() || descripcion.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generar un número de ticket aleatorio
        int numeroTicket = new Random().nextInt(900000) + 100000;

        // Mostrar mensaje de confirmación
        String mensaje = "Recibido su ticket, el número es: " + numeroTicket;
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();

        // Limpiar los campos después de enviar
        etNombreUsuario.setText("");
        etCorreoUsuario.setText("");
        etDescripcionProblema.setText("");
    }
}
