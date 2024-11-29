package com.demoApp.jamsstore;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demoApp.jamsstore.modelos_de_datos.Producto;

import java.util.List;

public class CarritoActivity extends AppCompatActivity {

    private RecyclerView carritoRecyclerView;
    private CarritoAdapter carritoAdapter;
    private List<Producto> listaCarrito;
    private TextView precioTotalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        carritoRecyclerView = findViewById(R.id.carrito_lista);
        precioTotalTextView = findViewById(R.id.precio_total);
        carritoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener la lista de productos del carrito usando el singleton
        listaCarrito = CarritoCompras.getInstance().obtenerProductos();

        carritoAdapter = new CarritoAdapter(listaCarrito);
        carritoRecyclerView.setAdapter(carritoAdapter);

        // Calculando el precio total
        double total = 0;
        for (Producto producto : listaCarrito) {
            total += Double.parseDouble(producto.getPrecio());
        }
        precioTotalTextView.setText("Total: $" + total);

        // Botón para continuar con el proceso de compra
        Button continuarButton = findViewById(R.id.siguiente_proceso);
        continuarButton.setOnClickListener(v -> {
            // Aquí puedes agregar lógica para procesar la compra, por ejemplo:
            // Vaciar el carrito si la compra se completa.
            // CarritoCompras.getInstance().vaciarCarrito();
            // Mostrar mensaje de éxito.
        });
    }
}
