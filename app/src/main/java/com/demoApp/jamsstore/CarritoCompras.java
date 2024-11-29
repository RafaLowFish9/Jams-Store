package com.demoApp.jamsstore;

import com.demoApp.jamsstore.modelos_de_datos.Producto;

import java.util.ArrayList;
import java.util.List;

public class CarritoCompras {

    // Lista para almacenar los productos en el carrito
    private List<Producto> listaProductosCarrito;

    // Variable estática para implementar el patrón Singleton
    private static CarritoCompras instance;

    // Constructor privado para evitar que se cree más de una instancia
    private CarritoCompras() {
        listaProductosCarrito = new ArrayList<>();
    }

    // Método para obtener la instancia única del carrito
    public static CarritoCompras getInstance() {
        if (instance == null) {
            instance = new CarritoCompras();
        }
        return instance;
    }

    // Método para agregar un producto al carrito
    public void agregarProducto(Producto producto) {
        listaProductosCarrito.add(producto);
    }

    // Método para obtener los productos del carrito
    public List<Producto> obtenerProductos() {
        return listaProductosCarrito;
    }

    // Método para vaciar el carrito (en caso de que sea necesario)
    public void vaciarCarrito() {
        listaProductosCarrito.clear();
    }
}
