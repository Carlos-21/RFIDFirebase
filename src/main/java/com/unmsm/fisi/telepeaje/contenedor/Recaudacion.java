/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.contenedor;

/**
 *
 * @author CARLOS
 */
public class Recaudacion {

    private double nMonto;
    private String sMes;
    private String sAño;
    private int nVehiculos;

    public double getnMonto() {
        return nMonto;
    }

    public void setnMonto(double nMonto) {
        this.nMonto = nMonto;
    }

    public String getsMes() {
        return sMes;
    }

    public void setsMes(String sMes) {
        this.sMes = sMes;
    }

    public String getsAño() {
        return sAño;
    }

    public void setsAño(String sAño) {
        this.sAño = sAño;
    }

    public int getnVehiculos() {
        return nVehiculos;
    }

    public void setnVehiculos(int nVehiculos) {
        this.nVehiculos = nVehiculos;
    }

}
