package com.example.jamsstore.modelos_de_datos;

public class CategoriaDatos {
    private String id_categoria;
    private String nombre;
    private String descripcion;

    //COnstructor para el firebase
    public CategoriaDatos(){}

    public CategoriaDatos(String id_categoria, String nombre, String descripcion){
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    //Getters
    public String getId_categoria() {
        return id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    //Setters
    public void setId_categoria(String id_categoria) {
        this.id_categoria = id_categoria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
