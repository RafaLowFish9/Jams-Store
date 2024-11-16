package com.example.jamsstore.modelos_de_datos;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {
    //Colección Usuario
    private String id_usuario;
    private String pais;
    private String nombre;
    private String apellido;
    private String correo_electronico;
    private String contrase_a;
    private String nombre_usuario;
    private String fecha_nacimiento;

    //Subcolecciones (Colecciones relacionadas a Usuario)
    private List<Resena> rese_as;
    private DireccionUsuario direccion;
    private Carrito carrito_de_compras;

    public Usuario() {}

    public Usuario(String id_usuario,String pais, String nombre, String apellido, String correo, String contrase_a, String nombre_usuario, String fecha_nacimiento,
                   List<Resena> rese_as, DireccionUsuario direccion, Carrito carrito_compras) {
        this.id_usuario = id_usuario;
        this.pais = pais;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo_electronico = correo;
        this.contrase_a = contrase_a;
        this.nombre_usuario = nombre_usuario;
        this.fecha_nacimiento = fecha_nacimiento;
        this.rese_as = rese_as;
        this.direccion = direccion;
        this.carrito_de_compras = carrito_compras;

    }

    //Getters
    public String getId_usuario() {
        return id_usuario;
    }

    public String getPais() {
        return pais;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public String getContrase_a() {
        return contrase_a;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public List<Resena> getRese_as() {
        return rese_as;
    }

    public DireccionUsuario getDireccion() {
        return direccion;
    }

    public Carrito get_carrito_de_compras() {
        return carrito_de_compras;
    }

    //Setters
    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public void setContrase_a(String contrase_a) {
        this.contrase_a = contrase_a;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setRese_as(List<Resena> rese_as) {
        this.rese_as = rese_as;
    }

    public void setDireccion(DireccionUsuario direccion) {
        this.direccion = direccion;
    }

    public void set_carrito_de_compras(Carrito carrito_de_compras) {
        this.carrito_de_compras = carrito_de_compras;
    }

}
