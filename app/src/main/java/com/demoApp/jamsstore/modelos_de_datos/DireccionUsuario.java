package com.demoApp.jamsstore.modelos_de_datos;

public class DireccionUsuario {
    private String id_usuario;
    private String pais;
    private String ciudad;
    private String codigo_postal;
    private String direccion;

    //Constructor vacío para firebase
    public DireccionUsuario(){}

    public DireccionUsuario(String id_usuario,String pais, String ciudad, String codigo_postal, String direccion){
        this.id_usuario = id_usuario;
        this.pais = pais;
        this.ciudad = ciudad;
        this.codigo_postal = codigo_postal;
        this.direccion = direccion;
    }

    //Getters
    public String getId_usuario() {
        return id_usuario;
    }

    public String getPais() {
        return pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public String getDireccion() {
        return direccion;
    }

    //Setters
    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
