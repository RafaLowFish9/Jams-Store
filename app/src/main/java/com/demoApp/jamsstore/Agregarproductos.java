package com.demoApp.jamsstore;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Agregarproductos extends AppCompatActivity {
    // Declarar las vistas y Firestore
    private EditText nombrePro, descripcionPro, precioPro, categoriaPro;
    private Button agregarPro;
    private ImageView imagenPro;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarproducto); // Vincular al XML

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();

        // Vincular vistas con los IDs del XML
        nombrePro = findViewById(R.id.nombrepro);
        descripcionPro = findViewById(R.id.descripcionpro);
        precioPro = findViewById(R.id.preciopro);
        categoriaPro = findViewById(R.id.categoriapro);  // Nueva vista para la categoría
        agregarPro = findViewById(R.id.agregarpro);
        imagenPro = findViewById(R.id.imagenpro);

        // Configurar botón para agregar producto
        agregarPro.setOnClickListener(v -> {
            String nombre = nombrePro.getText().toString().trim();
            String descripcion = descripcionPro.getText().toString().trim();
            String precio = precioPro.getText().toString().trim();
            String categoria = categoriaPro.getText().toString().trim();  // Obtener categoría

            if (validarCampos(nombre, descripcion, precio, categoria)) {
                guardarProductoEnFirestore(nombre, descripcion, precio, categoria);
            }
        });
    }

    // Validar campos del formulario
    private boolean validarCampos(String nombre, String descripcion, String precio, String categoria) {
        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(this, "El nombre del producto es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(descripcion)) {
            Toast.makeText(this, "La descripción del producto es obligatoria", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(precio)) {
            Toast.makeText(this, "El precio del producto es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(categoria)) {
            Toast.makeText(this, "La categoría del producto es obligatoria", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Guardar producto en Firestore
    private void guardarProductoEnFirestore(String nombre, String descripcion, String precio, String categoria) {
        // Crear un mapa con los datos del producto
        Map<String, Object> producto = new HashMap<>();
        producto.put("nombre", nombre);
        producto.put("descripcion", descripcion);
        producto.put("precio", precio);
        producto.put("categoria", categoria);  // Agregar la categoría

        // Guardar en la colección "productos" en Firestore
        db.collection("productos")
                .add(producto)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al agregar producto: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    // Limpiar los campos después de agregar un producto
    private void limpiarCampos() {
        nombrePro.setText("");
        descripcionPro.setText("");
        precioPro.setText("");
        categoriaPro.setText("");  // Limpiar el campo de categoría
    }
}
