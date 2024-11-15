package com.example.jamsstore.modelos_de_datos;

public class ReporteVentas {
    private String id_reporte_ventas;
    private int ventas_a_o; //Ventas x año
    private int proyeccion_a_o; //proyeccion de ventas x año

    //Llaves foráneas
    private Mes id_mes;
    private GerenteVentas id_gerente;

    //Constructor vacío para el firebase
    public ReporteVentas(){}

    public ReporteVentas(String id_reporte_ventas, int ventas_a_o, int proyeccion_a_o, Mes id_mes, GerenteVentas id_gerente){
        this.id_reporte_ventas = id_reporte_ventas;
        this.ventas_a_o = ventas_a_o;
        this.proyeccion_a_o = proyeccion_a_o;
        this.id_mes = id_mes;
        this.id_gerente = id_gerente;
    }

    //Getters
    public String getId_reporte_ventas() {
        return id_reporte_ventas;
    }

    public int getVentas_a_o() {
        return ventas_a_o;
    }

    public int getProyeccion_a_o() {
        return proyeccion_a_o;
    }

    public Mes getId_mes() {
        return id_mes;
    }

    public GerenteVentas getId_gerente() {
        return id_gerente;
    }

    //Setters
    public void setId_reporte_ventas(String id_reporte_ventas) {
        this.id_reporte_ventas = id_reporte_ventas;
    }

    public void setVentas_a_o(int ventas_a_o) {
        this.ventas_a_o = ventas_a_o;
    }

    public void setProyeccion_a_o(int proyeccion_a_o) {
        this.proyeccion_a_o = proyeccion_a_o;
    }

    public void setId_mes(Mes id_mes) {
        this.id_mes = id_mes;
    }

    public void setId_gerente(GerenteVentas id_gerente) {
        this.id_gerente = id_gerente;
    }

}
