package com.demoApp.jamsstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.demoApp.jamsstore.modelos_de_datos.Producto;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Producto> listaProductos;
    private OnProductoClickListener listener;

    // Constructor
    public ProductoAdapter(List<Producto> listaProductos, OnProductoClickListener listener) {
        this.listaProductos = listaProductos;
        this.listener = listener;
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflar el layout para cada item de producto
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductoViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);
        holder.bind(producto, listener);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        private TextView nombreTextView, descripcionTextView, precioTextView;
        private Button agregarCarritoButton;

        // Constructor del ViewHolder
        public ProductoViewHolder(View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreProducto);
            descripcionTextView = itemView.findViewById(R.id.descripcionProducto);
            precioTextView = itemView.findViewById(R.id.precioProducto);
            agregarCarritoButton = itemView.findViewById(R.id.agregarCarritoButton);
        }

        // Vincular datos con las vistas del ViewHolder
        public void bind(Producto producto, OnProductoClickListener listener) {
            nombreTextView.setText(producto.getNombre());
            descripcionTextView.setText(producto.getDescripcion());
            precioTextView.setText("$" + producto.getPrecio());
            agregarCarritoButton.setOnClickListener(v -> listener.onProductoClick(producto));
        }
    }

    // Interfaz para manejar el clic en el producto
    public interface OnProductoClickListener {
        void onProductoClick(Producto producto);
    }
}
