/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.fisi.telepeaje.contenedor;

/**
 *
 * @author Jorge Meza
 */
public class Pago {
    private double nMonto;
    private int nPago;
    private String sFecha;
    private String sHora;
    private String sVehiculo;

    public double getnMonto() {
        return nMonto;
    }

    public void setnMonto(double nMonto) {
        this.nMonto = nMonto;
    }

    public int getnPago() {
        return nPago;
    }

    public void setnPago(int nPago) {
        this.nPago = nPago;
    }

    public String getsFecha() {
        return sFecha;
    }

    public void setsFecha(String sFecha) {
        this.sFecha = sFecha;
    }

    public String getsHora() {
        return sHora;
    }

    public void setsHora(String sHora) {
        this.sHora = sHora;
    }

    public String getsVehiculo() {
        return sVehiculo;
    }

    public void setsVehiculo(String sVehiculo) {
        this.sVehiculo = sVehiculo;
    }
    
    
}
