package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Movimiento implements Serializable {

    private int id;
    private int tipo;
    private Date fechaOperacion;
    private String descripcion;
    private float importe;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movimiento(int id, int tipo, Date fechaOperacion, String descripcion, float importe, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        this.id = id;
        this.tipo = tipo;
        this.fechaOperacion = fechaOperacion;
        this.descripcion = descripcion;
        this.importe = importe;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public Movimiento(){
        super();
    }

    @Override
    public String toString(){
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        return "id: " + this.id + "\ntipo: " + this.tipo + "\nfecha operacion: " + formateador.format(this.fechaOperacion) + "\ndescripcion: " + this.descripcion + "\nimporte: " +
                this.importe + "\nid cuenta origen: " + this.cuentaOrigen.getId() + "\nid cuenta destino: " + this.cuentaDestino.getId();
    }
}
