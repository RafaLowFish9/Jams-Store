package com.appJamsMarket.jamsMarket.modelos_de_datos;

public class Producto {
    private String id_producto;
    private String nombre;
    private double valor;

    //Llave foránea
    private String id_categoria;

    //Constructor vacío para el firebase
    public Producto(){}

    public Producto(String id_producto, String nombre, double valor, String id_categoria){
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.valor = valor;
        this.id_categoria = id_categoria;
    }

    //Getters
    public String getId_producto() {
        return id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getValor() {
        return valor;
    }

    public String getId_categoria() {
        return id_categoria;
    }

    //Setters
    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setId_categoria(String id_categoria) {
        this.id_categoria = id_categoria;
    }
}
