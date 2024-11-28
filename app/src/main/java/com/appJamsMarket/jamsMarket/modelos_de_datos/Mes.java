package com.appJamsMarket.jamsMarket.modelos_de_datos;

public class Mes {
    private String id_mes;
    private String nombre_mes;

    //COnstructor vacío para firebase
    public Mes(){}

    public Mes(String id_mes, String nombre_mes){
        this.id_mes = id_mes;
        this.nombre_mes = nombre_mes;
    }

    //Getters
    public String getId_mes() {
        return id_mes;
    }

    public String getNombre_mes() {
        return nombre_mes;
    }

    public void setId_mes(String id_mes) {
        this.id_mes = id_mes;
    }

    public void setNombre_mes(String nombre_mes) {
        this.nombre_mes = nombre_mes;
    }
}
