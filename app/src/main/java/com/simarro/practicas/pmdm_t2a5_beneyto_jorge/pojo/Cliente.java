package com.simarro.practicas.pmdm_t2a5_beneyto_jorge.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable {

    private int id;
    private String nif;
    private String nombre;
    private String apellidos;
    private String claveSeguridad;
    private String email;
    private ArrayList<Cuenta> listaCuentas;

    public ArrayList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(ArrayList<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getClaveSeguridad() {
        return claveSeguridad;
    }

    public void setClaveSeguridad(String claveSeguridad) {
        this.claveSeguridad = claveSeguridad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente(int id, String nif, String nombre, String apellidos, String claveSeguridad, String email) {
        this.id = id;
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.claveSeguridad = claveSeguridad;
        this.email = email;
        this.listaCuentas = new ArrayList<Cuenta>();
    }

    public Cliente(){
        super();
    }

    @Override
    public String toString(){
        return "id: " + this.id + "\nnif: " + this.nif + "\nnombre: " + this.nombre + "\napellidos: " + this.apellidos;
    }
}
