package com.example.jamsstore.modelos_de_datos;

public class GerenteVentas {
    private String id_gerente;
    private String nombre_gerente;

    //Llave foránea
    private ReporteVentas id_reporte;

    //Constructor para el firebase
    public GerenteVentas(){}

    public GerenteVentas(String id_gerente, String nombre_gerente, ReporteVentas id_reporte)
    {
        this.id_gerente = id_gerente;
        this.nombre_gerente = nombre_gerente;
        this.id_reporte = id_reporte;
    }

    //Getters

    public String getId_gerente() {
        return id_gerente;
    }

    public String getNombre_gerente() {
        return nombre_gerente;
    }

    public ReporteVentas getId_reporte() {
        return id_reporte;
    }

    //Setters
    public void setId_gerente(String id_gerente) {
        this.id_gerente = id_gerente;
    }

    public void setNombre_gerente(String nombre_gerente) {
        this.nombre_gerente = nombre_gerente;
    }

    public void setId_reporte(ReporteVentas id_reporte) {
        this.id_reporte = id_reporte;
    }
}
