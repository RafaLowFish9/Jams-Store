package com.appJamsMarket.jamsMarket.modelos_de_datos;

import java.util.Date;

public class Resena {
    private String id_resena;
    private  String comentario;
    private Date fecha_publicacion;

    //Constructor vacío para firebase
    public Resena(){}

    public Resena(String id_resena,String comentario, Date fecha_publicacion){
        this.id_resena = id_resena;
        this.comentario = comentario;
        this.fecha_publicacion = fecha_publicacion;
    }

    //Getters
    public String getId_resena() {
        return id_resena;
    }

    public String getComentario() {
        return comentario;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    //Setters
    public void setId_resena(String id_resena) {
        this.id_resena = id_resena;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }
}
