package com.demoApp.jamsstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.inicio_app), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        ImageButton btn_seccion_celulares = findViewById(R.id.imagen_celulares);
        btn_seccion_celulares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inicio.this, ventana_vista_tipo_producto.class);
                startActivity(intent);
            }
        });

        TextView btn_seccion_categorias = findViewById(R.id.text_categoria);
        btn_seccion_categorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inicio.this, categorias.class);
                startActivity(intent);
            }
        });

        TextView btn_acceso_mi_cuenta = findViewById(R.id.mi_cuenta);
        btn_acceso_mi_cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inicio.this, mi_cuenta.class);
                startActivity(intent);
            }
        });

        TextView btnAccesoSoporte = findViewById(R.id.mi_soporte);
        btnAccesoSoporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inicio.this, contacto.class);
                startActivity(intent);
                finish();;
            }
        });
    }
}