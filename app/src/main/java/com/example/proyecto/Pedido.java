package com.example.proyecto;

public class Pedido {
    private String Ubicacion,estado,telefono,id;

    public Pedido(String ubicacion, String estado, String telefono, String id) {
        Ubicacion = ubicacion;
        this.estado = estado;
        this.telefono = telefono;
        this.id = id;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
