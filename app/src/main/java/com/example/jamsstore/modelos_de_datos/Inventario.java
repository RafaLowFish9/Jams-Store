package com.example.jamsstore.modelos_de_datos;

public class Inventario {
    private String id_inventario;
    private int stock_disponible;
    private int cant_maxima;
    private int cant_minima;

    //Llaves fóraneas
    private String id_producto;
    private String id_reporte_ventas;

    //Constructor vacío para firebase
    public Inventario(){}

    public Inventario(String id_inventario, int stock_disponible, int cant_maxima, int cant_minima, String id_producto, String id_reporte_ventas){
        this.id_inventario = id_inventario;
        this.stock_disponible = stock_disponible;
        this.cant_maxima = cant_maxima;
        this.cant_minima = cant_minima;
        this.id_producto = id_producto;
        this.id_reporte_ventas = id_reporte_ventas;
    }

    //Getters
    public String getId_inventario() {
        return id_inventario;
    }

    public int getStock_disponible() {
        return stock_disponible;
    }

    public int getCant_maxima() {
        return cant_maxima;
    }

    public int getCant_minima() {
        return cant_minima;
    }

    public String getId_producto() {
        return id_producto;
    }

    public String getId_reporte_ventas() {
        return id_reporte_ventas;
    }

    //Getters
    public void setId_inventario(String id_inventario) {
        this.id_inventario = id_inventario;
    }

    public void setStock_disponible(int stock_disponible) {
        this.stock_disponible = stock_disponible;
    }

    public void setCant_maxima(int cant_maxima) {
        this.cant_maxima = cant_maxima;
    }

    public void setCant_minima(int cant_minima) {
        this.cant_minima = cant_minima;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public void setId_reporte_ventas(String id_reporte_ventas) {
        this.id_reporte_ventas = id_reporte_ventas;
    }
}
