package com.appJamsMarket.jamsMarket.modelos_de_datos;

import java.util.List;
import java.util.Date;

public class Pedido {
    private String id_pedido;
    private Date fecha_pedido;
    private double valor;
    private DireccionUsuario direccion;
    private MetodoPago metodo_pago;

    //Llaves foráneas
    private List<Producto> producto;
    private Usuario usuario;

    //COnstructor vacío para el firebase
    public Pedido(){}

    public Pedido(String id_pedido, Date fecha_pedido, double valor, DireccionUsuario direccion, MetodoPago metodo_pago,
    List<Producto> producto, Usuario usuario) {
        this.id_pedido = id_pedido;
        this.fecha_pedido = fecha_pedido;
        this.valor = valor;
        this.direccion = direccion;
        this.metodo_pago = metodo_pago;
        this.producto = producto;
        this.usuario = usuario;
    }

    //Getters
    public String getId_pedido() {
        return id_pedido;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public double getValor() {
        return valor;
    }

    public DireccionUsuario getDireccion() {
        return direccion;
    }

    public MetodoPago getMetodo_pago() {
        return metodo_pago;
    }

    public List<Producto> getId_producto() {
        return producto;
    }

    public Usuario getId_usuario() {
        return usuario;
    }

    //Setters
    public void setId_pedido(String id_pedido) {
        this.id_pedido = id_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDireccion(DireccionUsuario direccion) {
        this.direccion = direccion;
    }

    public void setMetodo_pago(MetodoPago metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public void setId_producto(List<Producto> id_producto) {
        this.producto = id_producto;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.usuario = id_usuario;
    }
}
