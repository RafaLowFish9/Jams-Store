package com.demoApp.jamsstore.modelos_de_datos;

public class MetodoPago {
    private String id_metodo_pago;
    private String tipo;
    private double num_tarjeta;

    //Llave fóranea
    private Usuario usuario;

    //Constructor vacío para pasar a firebase
    public MetodoPago() {}

    public MetodoPago(String id_metodo_pago,String tipo, double num_tarjeta, Usuario usuario ){
        this.id_metodo_pago = id_metodo_pago;
        this.tipo = tipo;
        this.num_tarjeta = num_tarjeta;
        this.usuario = usuario;
    }

    //Getters
    public String getId_metodo_pago() {
        return id_metodo_pago;
    }

    public String getTipo() {
        return tipo;
    }

    public double getNum_tarjeta() {
        return num_tarjeta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    //Setters
    public void setId_metodo_pago(String id_metodo_pago) {
        this.id_metodo_pago = id_metodo_pago;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNum_tarjeta(double num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
