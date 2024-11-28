package com.appJamsMarket.jamsMarket.modelos_de_datos;

import java.util.List;

public class Carrito {
    private String id_carrito;
    private double costo_total;
    private  int cantidad;

    //Llave foránea
    private List<Producto> productos;

    //Constructor vacío para firebase
    public Carrito() {}

    public Carrito(String id_carrito ,double costo_total, int cantidad, List<Producto> productos) {

        this.id_carrito = id_carrito;
        this.costo_total = costo_total;
        this.cantidad = cantidad;
        this.productos = productos;
    }

    //Getters
    public String getId_carrito() {
        return id_carrito;
    }

    public double getCosto_total() {
        return costo_total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    //Setters
    public void setId_carrito(String id_carrito) {
        this.id_carrito = id_carrito;
    }

    public void setCosto_total(double costo_total) {
        this.costo_total = costo_total;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
