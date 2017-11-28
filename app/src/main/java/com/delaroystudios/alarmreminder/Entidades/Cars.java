package com.delaroystudios.alarmreminder.Entidades;

import java.io.Serializable;

/**
 * Created by Bayardo Moraga on 23/11/2017.
 */

public class Cars implements Serializable {
    private String id;
    private String marca;
    private String modelo;
    private String chasis;
    private String motor;
    private String anio;
    private String combustible;


    public Cars(String id, String marca, String modelo, String chasis, String motor, String anio, String combustible) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.chasis = chasis;
        this.motor = motor;
        this.anio = anio;
        this.combustible = combustible;
    }
    public Cars(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setGasolina(String combustible) {
        this.combustible = combustible;
    }

}