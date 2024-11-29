package com.demoApp.jamsstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demoApp.jamsstore.modelos_de_datos.Producto;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class VerProductosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductoAdapter adapter;
    private List<Producto> listaProductos;
    private FirebaseFirestore db;
    private Button btnVerCarrito; // Botón para navegar al carrito

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_productos);

        // Inicializar vistas
        recyclerView = findViewById(R.id.recyclerViewProductos);
        btnVerCarrito = findViewById(R.id.btnVerCarrito); // Asegúrate de que exista en el XML
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaProductos = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        // Obtener productos de Firestore
        db.collection("productos")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        String nombre = document.getString("nombre");
                        String descripcion = document.getString("descripcion");
                        String precio = document.getString("precio");
                        String categoria = document.getString("categoria");
                        String imagenUrl = document.getString("imagen_url"); // Si tienes imagen

                        Producto producto = new Producto(nombre, descripcion, precio, categoria, imagenUrl);
                        listaProductos.add(producto);
                    }
                    adapter = new ProductoAdapter(listaProductos, producto -> {
                        // Agregar producto al carrito
                        agregarAlCarrito(producto);
                    });
                    recyclerView.setAdapter(adapter);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(VerProductosActivity.this, "Error al cargar productos", Toast.LENGTH_SHORT).show();
                });

        btnVerCarrito.setOnClickListener(v -> {
            // Crear un Intent para navegar a la actividad CarritoActivity
            Intent intent = new Intent(VerProductosActivity.this, CarritoActivity.class);
            startActivity(intent);  // Iniciar la actividad del carrito
        });
    }

    private void agregarAlCarrito(Producto producto) {
        // Obtener la instancia del carrito y agregar el producto
        CarritoCompras.getInstance().agregarProducto(producto);

        // Mostrar un mensaje de éxito
        Toast.makeText(this, "Producto agregado al carrito: " + producto.getNombre(), Toast.LENGTH_SHORT).show();
    }

}

