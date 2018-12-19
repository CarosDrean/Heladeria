package com.drean.projects.heladeria.pojo;

public class Pedido {
    private int id;
    private String nombreHelado;
    private String imgHelado;
    private String nombre;
    private int celular;
    private int cantidad;
    private double precioUnidad;
    private double igv;
    private String fecha;
    private double total;

    public Pedido() {
    }

    public Pedido(int id, String nombreHelado, String imgHelado, String nombre, int celular, int cantidad, double precioUnidad, double igv, String fecha, double total) {
        this.id = id;
        this.nombreHelado = nombreHelado;
        this.imgHelado = imgHelado;
        this.nombre = nombre;
        this.celular = celular;
        this.cantidad = cantidad;
        this.precioUnidad = precioUnidad;
        this.igv = igv;
        this.fecha = fecha;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreHelado() {
        return nombreHelado;
    }

    public void setNombreHelado(String nombreHelado) {
        this.nombreHelado = nombreHelado;
    }

    public String getImgHelado() {
        return imgHelado;
    }

    public void setImgHelado(String imgHelado) {
        this.imgHelado = imgHelado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
