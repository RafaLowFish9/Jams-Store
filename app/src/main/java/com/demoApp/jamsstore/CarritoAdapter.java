package com.demoApp.jamsstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.demoApp.jamsstore.modelos_de_datos.Producto;

import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder> {

    private List<Producto> listaCarrito;

    public CarritoAdapter(List<Producto> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }

    @Override
    public CarritoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrito, parent, false);
        return new CarritoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarritoViewHolder holder, int position) {
        Producto producto = listaCarrito.get(position);
        holder.nombreProducto.setText(producto.getNombre());
        holder.precioProducto.setText("$" + producto.getPrecio());
        // Si tienes imágenes, puedes cargarlas aquí con una librería como Picasso o Glide.
    }

    @Override
    public int getItemCount() {
        return listaCarrito.size();
    }

    public static class CarritoViewHolder extends RecyclerView.ViewHolder {

        TextView nombreProducto;
        TextView precioProducto;

        public CarritoViewHolder(View itemView) {
            super(itemView);
            nombreProducto = itemView.findViewById(R.id.nombre_producto);
            precioProducto = itemView.findViewById(R.id.precio_producto);
        }
    }
}

