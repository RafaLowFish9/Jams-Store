package com.appJamsMarket.jamsMarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class mi_cuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mi_cuenta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mi_cuenta), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btn_volver = findViewById(R.id.boton_volver);
        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mi_cuenta.this, inicio.class);
                startActivity(intent);
            }
        });

        TextView btn_entrar_mi_perfil = findViewById(R.id.mi_perfil);
        btn_entrar_mi_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mi_cuenta.this, perfil.class);
                startActivity(intent);
            }
        });


        TextView btn_entrar_direccion_envio = findViewById(R.id.direccion_envio);
        btn_entrar_direccion_envio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mi_cuenta.this, direccion.class);
                startActivity(intent);
            }
        });

        TextView btn_cerrar_sesion = findViewById(R.id.cerrar_sesion);
        btn_cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mi_cuenta.this, login.class);
                startActivity(intent);
            }
        });


    }
}